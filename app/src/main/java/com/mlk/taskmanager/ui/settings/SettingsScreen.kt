package com.mlk.taskmanager.ui.settings

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
                ProfileCard()
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

            // Appearance Section
            item {
                SettingsSection(
                    title = "Appearance",
                    icon = Icons.Filled.Palette,
                    iconTint = Color(0xFFFF4081)
                ) {
                    SettingsItem(
                        title = "Theme",
                        subtitle = "Choose your preferred theme",
                        icon = Icons.Outlined.DarkMode,
                        onClick = { showThemePicker = true }
                    )

                    SettingsSwitch(
                        title = "Dynamic Colors",
                        subtitle = "Use system accent colors",
                        icon = Icons.Outlined.ColorLens,
                        checked = uiState.useDynamicColors,
                        onCheckedChange = { viewModel.setDynamicColors(it) }
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

                    SettingsItem(
                        title = "Default Reminder Time",
                        subtitle = uiState.defaultReminderTime.toString(),
                        icon = Icons.Outlined.Schedule,
                        onClick = { /* Show time picker */ }
                    )
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
                        subtitle = "Backup or restore your data",
                        icon = Icons.Outlined.Backup,
                        onClick = { showBackupDialog = true }
                    )

                    SettingsItem(
                        title = "Clear Data",
                        subtitle = "Delete all app data",
                        icon = Icons.Outlined.DeleteForever,
                        onClick = { /* Show clear data confirmation */ }
                    )
                }
            }

            // About Section
            item {
                SettingsSection(
                    title = "About",
                    icon = Icons.Filled.Info,
                    iconTint = Color(0xFF9C27B0)
                ) {
                    SettingsItem(
                        title = "Version",
                        subtitle = "1.0.0",
                        icon = Icons.Outlined.Update,
                        onClick = { }
                    )

                    SettingsItem(
                        title = "Privacy Policy",
                        subtitle = "Read our privacy policy",
                        icon = Icons.Outlined.Security,
                        onClick = { }
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }

    if (showThemePicker) {
        ThemePickerDialog(
            onDismiss = { showThemePicker = false },
            onThemeSelected = { theme ->
                viewModel.setDarkMode(when (theme) {
                    "dark" -> true
                    "light" -> false
                    else -> false // system default
                })
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

@Composable
private fun ProfileCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column {
                Text(
                    text = "User",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "user@example.com",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun SettingsSection(
    title: String,
    icon: ImageVector,
    iconTint: Color,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surface)
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
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        content()
    }
}

@Composable
private fun SettingsItem(
    title: String,
    subtitle: String,
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
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
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
    }
}

@Composable
private fun SettingsSwitch(
    title: String,
    subtitle: String,
    icon: ImageVector,
    checked: Boolean,
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
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
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
private fun ThemePickerDialog(
    onDismiss: () -> Unit,
    onThemeSelected: (String) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Choose Theme")
        },
        text = {
            Column {
                SettingsItem(
                    title = "Light",
                    subtitle = "Light theme for your app",
                    icon = Icons.Default.LightMode,
                    onClick = { 
                        onThemeSelected("light")
                        onDismiss()
                    }
                )
                SettingsItem(
                    title = "Dark",
                    subtitle = "Dark theme for your app",
                    icon = Icons.Default.DarkMode,
                    onClick = { 
                        onThemeSelected("dark")
                        onDismiss()
                    }
                )
                SettingsItem(
                    title = "System",
                    subtitle = "Follow system theme",
                    icon = Icons.Default.Settings,
                    onClick = { 
                        onThemeSelected("system")
                        onDismiss()
                    }
                )
            }
        },
        confirmButton = { },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
private fun BackupDialog(
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Backup & Restore")
        },
        text = {
            Column {
                SettingsItem(
                    title = "Backup Data",
                    subtitle = "Create a backup of your data",
                    icon = Icons.Default.Backup,
                    onClick = { 
                        // Handle backup
                        onDismiss()
                    }
                )
                SettingsItem(
                    title = "Restore Data",
                    subtitle = "Restore from a backup",
                    icon = Icons.Default.Restore,
                    onClick = { 
                        // Handle restore
                        onDismiss()
                    }
                )
            }
        },
        confirmButton = { },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
private fun CategoryDialog(
    onDismiss: () -> Unit,
    categories: List<String>,
    onAddCategory: (String) -> Unit,
    onRemoveCategory: (String) -> Unit
) {
    var newCategory by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Manage Categories") },
        text = {
            Column {
                OutlinedTextField(
                    value = newCategory,
                    onValueChange = { newCategory = it },
                    label = { Text("New Category") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                // List of existing categories
                Column {
                    categories.forEach { category ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(category)
                            IconButton(onClick = { onRemoveCategory(category) }) {
                                Icon(Icons.Default.Delete, contentDescription = "Delete")
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                if (newCategory.isNotEmpty()) {
                    onAddCategory(newCategory)
                    newCategory = ""
                }
                onDismiss()
            }) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}