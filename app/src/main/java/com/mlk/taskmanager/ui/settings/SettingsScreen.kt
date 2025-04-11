package com.mlk.taskmanager.ui.settings

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.mlk.taskmanager.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    var showThemePicker by remember { mutableStateOf(false) }
    var showBackupDialog by remember { mutableStateOf(false) }
    var showCategoryDialog by remember { mutableStateOf(false) }

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
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
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

                    SettingsItem(
                        title = "Default Radius",
                        subtitle = "${uiState.defaultLocationRadius.toInt()} meters",
                        icon = Icons.Outlined.RadioButtonChecked,
                        onClick = { /* Show radius picker */ }
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
                                title = "Synchroniser maintenant",
                                description = "Synchroniser toutes vos routines avec Google Calendar",
                                icon = Icons.Default.Sync,
                                onClick = { viewModel.syncAllRoutines() }
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
                        onClick = { /* Show licenses */ }
                    )

                    SettingsItem(
                        title = "Privacy Policy",
                        subtitle = "Read our privacy policy",
                        icon = Icons.Outlined.Shield,
                        onClick = { /* Show privacy policy */ }
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
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Profile Image
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    if (uiState.isUserLoggedIn && uiState.currentUser != null) {
                        // Display first letter of email as avatar if no profile picture
                        val userEmail = uiState.currentUser?.email ?: ""
                        if (userEmail.isNotEmpty()) {
                            Text(
                                text = userEmail.first().uppercase(),
                                style = MaterialTheme.typography.headlineMedium,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Profile",
                                modifier = Modifier.size(32.dp),
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    } else {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile",
                            modifier = Modifier.size(32.dp),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
                
                Spacer(modifier = Modifier.width(16.dp))
                
                // User Info
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    if (uiState.isUserLoggedIn && uiState.currentUser != null) {
                        Text(
                            text = uiState.currentUser?.name ?: "Utilisateur",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        uiState.currentUser?.email?.let { email ->
                            Text(
                                text = email,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    } else {
                        Text(
                            text = "Non connecté",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Connectez-vous pour synchroniser vos données",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            if (uiState.isUserLoggedIn) {
                // Logout Button
                Button(
                    onClick = { 
                        viewModel.logout()
                        navController.navigate("login") {
                            popUpTo("home") { inclusive = true }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer,
                        contentColor = MaterialTheme.colorScheme.onErrorContainer
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Logout,
                        contentDescription = "Logout",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Se déconnecter")
                }
            } else {
                // Login Button
                Button(
                    onClick = { navController.navigate("login") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.Login,
                        contentDescription = "Login",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Se connecter")
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
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(24.dp)
                )
                
                Spacer(modifier = Modifier.width(8.dp))
                
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            
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
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant
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
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
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
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column {
                themes.forEach { theme ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onThemeSelected(theme) }
                            .padding(vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = false,
                            onClick = { onThemeSelected(theme) }
                        )
                        
                        Spacer(modifier = Modifier.width(8.dp))
                        
                        Text(
                            text = theme,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
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
                text = "Manage Categories",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column {
                OutlinedTextField(
                    value = newCategory,
                    onValueChange = { newCategory = it },
                    label = { Text("New Category") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                if (newCategory.isNotEmpty()) {
                                    onAddCategory(newCategory)
                                    newCategory = ""
                                }
                            },
                            enabled = newCategory.isNotEmpty()
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add Category"
                            )
                        }
                    }
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "Current Categories",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                categories.forEach { category ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = category,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.weight(1f)
                        )
                        
                        IconButton(onClick = { onRemoveCategory(category) }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Remove Category",
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Done")
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
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(24.dp)
                .padding(end = 16.dp)
        )
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge
            )
            
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange
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
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(24.dp)
                .padding(end = 16.dp)
        )
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge
            )
            
            Text(
                text = value,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
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
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(24.dp)
                .padding(end = 16.dp)
        )
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge
            )
            
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}