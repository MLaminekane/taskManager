package com.mlk.taskmanager.ui.settings

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.BorderStroke
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.mlk.taskmanager.ui.navigation.Screen
import com.mlk.taskmanager.ui.theme.Background
import com.mlk.taskmanager.ui.theme.TextColor
import com.mlk.taskmanager.ui.theme.PrimaryColor
import com.mlk.taskmanager.ui.theme.SecondaryColor
import com.mlk.taskmanager.ui.theme.AccentColor

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    var showThemePicker by remember { mutableStateOf(false) }
    var showBackupDialog by remember { mutableStateOf(false) }
    var showCategoryDialog by remember { mutableStateOf(false) }
    var showLicensesDialog by remember { mutableStateOf(false) }
    var showPrivacyPolicyDialog by remember { mutableStateOf(false) }

    val uiState by viewModel.uiState.collectAsState()
    
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            viewModel.handleSignInResult(result)
        }
    )

    val notificationPermissionState = rememberPermissionState(
        android.Manifest.permission.POST_NOTIFICATIONS
    )

    val locationPermissionState = rememberPermissionState(
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Settings",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = TextColor
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Background,
                    titleContentColor = TextColor
                )
            )
        },
        containerColor = Background
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
                .background(Background),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Profile Section
            item {
                ProfileCard(navController, viewModel)
            }

            // Notifications Section
            item {
                SettingsSection(
                    title = "Notifications",
                    icon = Icons.Filled.Notifications,
                    iconTint = Color(0xFF613BE7)
                ) {
                    SettingsSwitch(
                        title = "Enable Notifications",
                        subtitle = "Get notified about your tasks and reminders",
                        icon = Icons.Outlined.NotificationsActive,
                        checked = notificationPermissionState.status.isGranted && uiState.areNotificationsEnabled,
                        onCheckedChange = { checked ->
                            if (checked) {
                                notificationPermissionState.launchPermissionRequest()
                            }
                            viewModel.setNotificationsEnabled(checked)
                        }
                    )

                    SettingsSwitch(
                        title = "Sound",
                        subtitle = "Play sound for notifications",
                        icon = Icons.Outlined.VolumeUp,
                        checked = uiState.isSoundEnabled,
                        onCheckedChange = { viewModel.setSoundEnabled(it) }
                    )

                    SettingsSwitch(
                        title = "Vibrate",
                        subtitle = "Vibrate for notifications",
                        icon = Icons.Outlined.Vibration,
                        checked = uiState.isVibrationEnabled,
                        onCheckedChange = { viewModel.setVibrationEnabled(it) }
                    )
                }
            }

            // Location Section
            item {
                SettingsSection(
                    title = "Location",
                    icon = Icons.Filled.LocationOn,
                    iconTint = Color(0xFF4CAF50)
                ) {
                    SettingsSwitch(
                        title = "Location Services",
                        subtitle = "Allow app to access your location for location-based reminders",
                        icon = Icons.Outlined.MyLocation,
                        checked = locationPermissionState.status.isGranted && uiState.isLocationEnabled,
                        onCheckedChange = { checked ->
                            if (checked) {
                                locationPermissionState.launchPermissionRequest()
                            }
                            viewModel.setLocationEnabled(checked)
                        }
                    )
                }
            }

            // Task Management Section
            item {
                SettingsSection(
                    title = "Task Management",
                    icon = Icons.Filled.Assignment,
                    iconTint = Color(0xFF2196F3)
                ) {
                    SettingsItem(
                        title = "Categories",
                        subtitle = "Manage task categories",
                        icon = Icons.Outlined.Category,
                        onClick = { showCategoryDialog = true }
                    )
                }
            }

            // Google Calendar Section
            item {
                SettingsSection(
                    title = "Google Calendar",
                    icon = Icons.Filled.CalendarToday,
                    iconTint = Color(0xFF9C27B0)
                ) {
                    // Switch pour activer/désactiver la synchro
                    SettingsSwitch(
                        title = "Synchroniser avec Google Calendar",
                        subtitle = "Vos routines seront automatiquement synchronisées avec Google Calendar",
                        icon = Icons.Outlined.Sync,
                        checked = uiState.isCalendarSyncEnabled,
                        onCheckedChange = { enabled ->
                            if (enabled && !uiState.isGoogleSignedIn) {
                                // Si on active la synchro mais qu'on n'est pas connecté, lancer la connexion
                                val signInClient = viewModel.signInToGoogle()
                                launcher.launch(signInClient.signInIntent)
                            } else {
                                // Sinon, juste changer le paramètre
                                viewModel.setCalendarSyncEnabled(enabled)
                            }
                        }
                    )

                    if (uiState.isCalendarSyncEnabled) {
                        if (uiState.isGoogleSignedIn) {
                            // Afficher les informations du compte connecté
                            ListPreference(
                                title = "Compte Google",
                                value = uiState.googleAccountEmail ?: "Non connecté",
                                icon = Icons.Default.AccountCircle,
                                onClick = { /* Ne rien faire, juste informatif */ }
                            )
                            
                            ButtonPreference(
                                title = "Synchroniser les routines",
                                description = "Synchroniser toutes vos routines avec Google Calendar",
                                icon = Icons.Default.Sync,
                                onClick = { viewModel.syncAllRoutines() }
                            )
                            
                            ButtonPreference(
                                title = "Synchroniser les tâches",
                                description = "Synchroniser toutes vos tâches avec Google Calendar",
                                icon = Icons.Default.Task,
                                onClick = { viewModel.syncAllTasks() }
                            )
                            
                            ButtonPreference(
                                title = "Tout synchroniser",
                                description = "Synchroniser toutes vos routines et tâches avec Google Calendar",
                                icon = Icons.Default.SyncAlt,
                                onClick = { viewModel.syncAll() }
                            )
                            
                            ButtonPreference(
                                title = "Se déconnecter",
                                description = "Se déconnecter de Google Calendar",
                                icon = Icons.Default.ExitToApp,
                                onClick = { viewModel.signOutFromGoogle() }
                            )
                        } else {
                            // Afficher un message si la synchronisation est activée mais pas connecté
                            ButtonPreference(
                                title = "Se connecter à Google",
                                description = "Connexion requise pour synchroniser avec Google Calendar",
                                icon = Icons.Default.Login,
                                onClick = { 
                                    val signInClient = viewModel.signInToGoogle()
                                    launcher.launch(signInClient.signInIntent)
                                }
                            )
                        }
                    }
                    
                    if (uiState.isSyncing) {
                        LinearProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        )
                    }
                    
                    if (uiState.syncError != null) {
                        Text(
                            text = uiState.syncError ?: "",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                    
                    if (uiState.lastSyncMessage != null) {
                        Text(
                            text = uiState.lastSyncMessage ?: "",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
            }

            // Data Management Section
            item {
                SettingsSection(
                    title = "Data Management",
                    icon = Icons.Filled.Storage,
                    iconTint = Color(0xFFFF9800)
                ) {
                    SettingsItem(
                        title = "Backup & Restore",
                        subtitle = "Save and restore your data",
                        icon = Icons.Outlined.Backup,
                        onClick = { showBackupDialog = true }
                    )

                    SettingsItem(
                        title = "Clear All Data",
                        subtitle = "Delete all tasks, routines, and settings",
                        icon = Icons.Outlined.DeleteForever,
                        onClick = { /* Show confirmation dialog */ }
                    )
                }
            }

            // About Section
            item {
                SettingsSection(
                    title = "About",
                    icon = Icons.Filled.Info,
                    iconTint = Color(0xFF607D8B)
                ) {
                    SettingsItem(
                        title = "Version",
                        subtitle = "1.0.0",
                        icon = Icons.Outlined.Update,
                        onClick = { /* Show app info */ }
                    )

                    SettingsItem(
                        title = "Licenses",
                        subtitle = "Third-party licenses",
                        icon = Icons.Outlined.Description,
                        onClick = { showLicensesDialog = true }
                    )

                    SettingsItem(
                        title = "Privacy Policy",
                        subtitle = "Read our privacy policy",
                        icon = Icons.Outlined.Shield,
                        onClick = { showPrivacyPolicyDialog = true }
                    )
                }
            }
        }

        if (showThemePicker) {
            ThemePickerDialog(
                onDismiss = { showThemePicker = false },
                onThemeSelected = { theme ->
                    // Handle theme selection
                    showThemePicker = false
                }
            )
        }

        if (showBackupDialog) {
            BackupDialog(
                onDismiss = { showBackupDialog = false }
            )
        }

        if (showCategoryDialog) {
            CategoryDialog(
                onDismiss = { showCategoryDialog = false },
                categories = uiState.categories,
                onAddCategory = { viewModel.addCategory(it) },
                onRemoveCategory = { viewModel.removeCategory(it) }
            )
        }

        if (showLicensesDialog) {
            LicensesDialog(
                onDismiss = { showLicensesDialog = false }
            )
        }

        if (showPrivacyPolicyDialog) {
            PrivacyPolicyDialog(
                onDismiss = { showPrivacyPolicyDialog = false }
            )
        }
    }
}

@Composable
fun ProfileCard(
    navController: NavController,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Background.copy(alpha = 0.6f),
            contentColor = TextColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            // User info section
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // User avatar
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(PrimaryColor.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Avatar",
                        tint = PrimaryColor,
                        modifier = Modifier.size(32.dp)
                    )
                }
                
                Spacer(modifier = Modifier.width(16.dp))
                
                // User name and email
                Column {
                    Text(
                        text = if (uiState.isUserLoggedIn && uiState.currentUser?.name?.isNotBlank() == true) 
                                  uiState.currentUser?.name ?: "Utilisateur"
                              else 
                                  "Utilisateur",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = TextColor
                        )
                    )
                    
                    Text(
                        text = if (uiState.isUserLoggedIn && uiState.currentUser?.email?.isNotBlank() == true)
                                  uiState.currentUser?.email ?: "Non connecté"
                              else
                                  "Non connecté",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextColor.copy(alpha = 0.7f)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Authentication actions
            if (uiState.isUserLoggedIn) {
                // Si l'utilisateur est connecté, afficher le bouton de déconnexion
                Button(
                    onClick = { 
                        viewModel.logout()
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AccentColor,
                        contentColor = TextColor
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.Logout,
                        contentDescription = "Se déconnecter",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Se déconnecter")
                }
            } else {
                // Si l'utilisateur n'est pas connecté, afficher les boutons de connexion et d'inscription
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Bouton de connexion
                    Button(
                        onClick = { navController.navigate(Screen.Login.route) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PrimaryColor,
                            contentColor = TextColor
                        ),
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Login,
                            contentDescription = "Se connecter",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Connexion")
                    }
                    
                    // Bouton d'inscription
                    Button(
                        onClick = { navController.navigate(Screen.Register.route) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = SecondaryColor,
                            contentColor = Color.Black
                        ),
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            imageVector = Icons.Default.PersonAdd,
                            contentDescription = "S'inscrire",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Inscription")
                    }
                }
            }
        }
    }
}

@Composable
fun SettingsSection(
    title: String,
    icon: ImageVector,
    iconTint: Color,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Background.copy(alpha = 0.6f),
            contentColor = TextColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            // Section header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(SecondaryColor.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = PrimaryColor,
                        modifier = Modifier.size(24.dp)
                    )
                }
                
                Spacer(modifier = Modifier.width(16.dp))
                
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = TextColor
                    )
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Section content
            content()
        }
    }
}

@Composable
fun SettingsItem(
    title: String,
    subtitle: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = PrimaryColor,
            modifier = Modifier.size(24.dp)
        )
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = TextColor
            )
            
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = TextColor.copy(alpha = 0.7f)
            )
        }
        
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = TextColor.copy(alpha = 0.5f)
        )
    }
}

@Composable
fun SettingsSwitch(
    title: String,
    subtitle: String,
    icon: ImageVector,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = PrimaryColor,
            modifier = Modifier.size(24.dp)
        )
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = TextColor
            )
            
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = TextColor.copy(alpha = 0.7f)
            )
        }
        
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = PrimaryColor,
                checkedTrackColor = PrimaryColor.copy(alpha = 0.5f),
                uncheckedThumbColor = TextColor.copy(alpha = 0.7f),
                uncheckedTrackColor = Background
            )
        )
    }
}

@Composable
fun ThemePickerDialog(
    onDismiss: () -> Unit,
    onThemeSelected: (String) -> Unit
) {
    val themes = listOf("System", "Light", "Dark")
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Choose Theme",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = TextColor
                )
            )
        },
        text = {
            Column {
                themes.forEach { theme ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { 
                                onThemeSelected(theme)
                                onDismiss()
                            }
                            .padding(vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = false,
                            onClick = {
                                onThemeSelected(theme)
                                onDismiss()
                            },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = PrimaryColor,
                                unselectedColor = TextColor.copy(alpha = 0.6f)
                            )
                        )
                        
                        Spacer(modifier = Modifier.width(8.dp))
                        
                        Text(
                            text = theme,
                            style = MaterialTheme.typography.bodyLarge,
                            color = TextColor
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = onDismiss,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = PrimaryColor
                )
            ) {
                Text("Cancel")
            }
        },
        containerColor = Background,
        titleContentColor = TextColor,
        textContentColor = TextColor
    )
}

@Composable
fun BackupDialog(
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Backup & Restore",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Create backups of your data or restore from an existing backup.")
                
                Button(
                    onClick = { /* Create backup */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Create Backup")
                }
                
                Button(
                    onClick = { /* Restore from backup */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Restore from Backup")
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun CategoryDialog(
    onDismiss: () -> Unit,
    categories: List<String>,
    onAddCategory: (String) -> Unit,
    onRemoveCategory: (String) -> Unit
) {
    var newCategory by remember { mutableStateOf("") }
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Gérer les catégories",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Champ de saisie pour ajouter une nouvelle catégorie
                OutlinedTextField(
                    value = newCategory,
                    onValueChange = { newCategory = it },
                    label = { Text("Nouvelle catégorie") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                if (newCategory.isNotBlank()) {
                                    onAddCategory(newCategory)
                                    newCategory = ""
                                }
                            },
                            enabled = newCategory.isNotBlank()
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Ajouter",
                                tint = if (newCategory.isNotBlank()) 
                                    MaterialTheme.colorScheme.primary 
                                else 
                                    Color.Gray.copy(alpha = 0.5f)
                            )
                        }
                    }
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Liste des catégories existantes
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    itemsIndexed(categories) { _, category ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = category,
                                style = MaterialTheme.typography.bodyLarge
                            )
                            
                            IconButton(
                                onClick = { onRemoveCategory(category) }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Supprimer",
                                    tint = Color.Red
                                )
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss
            ) {
                Text("Fermer")
            }
        }
    )
}

@Composable
fun LicensesDialog(
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Licenses",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(text = "This app uses the following third-party libraries:")
                
                Text(text = "• AndroidX")
                Text(text = "• Compose")
                Text(text = "• Hilt")
                Text(text = "• Accompanist")
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "OK")
            }
        }
    )
}

@Composable
fun PrivacyPolicyDialog(
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Privacy Policy",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(text = "This app collects the following data:")
                
                Text(text = "• Task and routine data")
                Text(text = "• User preferences")
                Text(text = "• Device information")
                
                Text(text = "This data is used to:")
                
                Text(text = "• Provide app functionality")
                Text(text = "• Improve app performance")
                Text(text = "• Personalize app experience")
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "OK")
            }
        }
    )
}

@Composable
fun SettingsSectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Medium,
        color = TextColor,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
fun SwitchPreference(
    title: String,
    description: String,
    icon: ImageVector,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = PrimaryColor,
            modifier = Modifier
                .size(24.dp)
                .padding(end = 16.dp)
        )
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = TextColor
            )
            
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = TextColor.copy(alpha = 0.7f)
            )
        }
        
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = PrimaryColor,
                checkedTrackColor = PrimaryColor.copy(alpha = 0.5f),
                uncheckedThumbColor = TextColor.copy(alpha = 0.7f),
                uncheckedTrackColor = Background
            )
        )
    }
}

@Composable
fun ListPreference(
    title: String,
    value: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = PrimaryColor,
            modifier = Modifier
                .size(24.dp)
                .padding(end = 16.dp)
        )
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = TextColor
            )
            
            Text(
                text = value,
                style = MaterialTheme.typography.bodySmall,
                color = TextColor.copy(alpha = 0.7f)
            )
        }
        
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            tint = TextColor.copy(alpha = 0.6f)
        )
    }
}

@Composable
fun ButtonPreference(
    title: String,
    description: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = PrimaryColor,
            modifier = Modifier
                .size(24.dp)
                .padding(end = 16.dp)
        )
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = TextColor
            )
            
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = TextColor.copy(alpha = 0.7f)
            )
        }
    }
}