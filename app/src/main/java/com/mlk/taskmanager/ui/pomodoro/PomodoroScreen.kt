package com.mlk.taskmanager.ui.pomodoro

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import com.mlk.taskmanager.ui.theme.Background
import com.mlk.taskmanager.ui.theme.TextColor
import com.mlk.taskmanager.ui.theme.PrimaryColor
import com.mlk.taskmanager.ui.theme.SecondaryColor
import com.mlk.taskmanager.ui.theme.AccentColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

/**
 * Écran Pomodoro
 * Permet à l'utilisateur de gérer des sessions de travail/pause selon la technique Pomodoro
 * 
 * @param navController Contrôleur de navigation
 * @param viewModel ViewModel de gestion du Pomodoro
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PomodoroScreen(
    navController: NavController,
    viewModel: PomodoroViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    // demander la permission pour le mode Ne pas déranger
    if (uiState.showPermissionDialog) {
        AlertDialog(
            onDismissRequest = { viewModel.permissionDialogShown() },
            containerColor = Color(0xFF121212),
            titleContentColor = TextColor,
            textContentColor = TextColor,
            title = { Text("Permission requise") },
            text = { 
                Text("Pour activer le mode Ne pas déranger, l'application a besoin d'une permission spéciale. Voulez-vous l'accorder maintenant?") 
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.permissionDialogShown()
                        viewModel.getNotificationPolicyAccessIntent()?.let { intent ->
                            context.startActivity(intent)
                        }
                    }
                ) {
                    Text("Oui", color = PrimaryColor)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { viewModel.permissionDialogShown() }
                ) {
                    Text("Non", color = PrimaryColor)
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Mode Focus",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Retour", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Background,
                    titleContentColor = TextColor
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Timer avec cercle de progression
            PomodoroTimer(
                minutes = uiState.minutes,
                seconds = uiState.seconds,
                isBreak = uiState.isBreak
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Boutons de contrôle
            PomodoroControls(
                isRunning = uiState.isRunning,
                onStartPause = { 
                    if (uiState.isRunning) viewModel.pauseTimer() else viewModel.startTimer()
                },
                onReset = { viewModel.resetTimer() },
                onSkip = { viewModel.skipSession() }
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Carte de réglages
            PomodoroSettings(
                dndEnabled = uiState.dndEnabled,
                notificationBlocked = uiState.notificationBlocked,
                onDndToggle = { viewModel.toggleDnd() },
                onNotificationBlockToggle = { viewModel.toggleNotificationBlocking() }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Carte de statistiques
            PomodoroStatistics(
                completedSessions = uiState.completedSessions,
                totalFocusMinutes = uiState.totalFocusMinutes,
                focusRate = uiState.focusRate
            )
            
            // Affichage des erreurs s'il y en a
            if (uiState.error != null) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = uiState.error!!,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Red
                )
            }
        }
    }
}

/**
 * Composant d'affichage du minuteur
 * 
 * @param minutes Minutes restantes
 * @param seconds Secondes restantes
 * @param isBreak Indique si c'est une période de pause
 */
@Composable
private fun PomodoroTimer(
    minutes: Int,
    seconds: Int,
    isBreak: Boolean
) {
    Box(
        modifier = Modifier
            .size(250.dp)
            .padding(bottom = 24.dp)
            .clip(CircleShape)
            .background(
                color = if (isBreak) AccentColor.copy(alpha = 0.2f) else PrimaryColor.copy(alpha = 0.2f)
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Affichage du temps restant
            Text(
                // Formatage du temps au format MM:SS avec les secondes toujours sur deux chiffres
                text = "$minutes:${String.format("%02d", seconds)}",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = if (isBreak) AccentColor else PrimaryColor
            )
            
            // Indication du mode (Focus ou Pause)
            Text(
                text = if (isBreak) "Pause" else "Focus",
                color = if (isBreak) Color(0xFFFFA07A) else Color(0xFFFFC107)
            )
        }
    }
}

/**
 * Boutons de contrôle du minuteur
 * 
 * @param isRunning Indique si le minuteur est en cours
 * @param onStartPause Action pour démarrer/mettre en pause le minuteur
 * @param onReset Action pour réinitialiser le minuteur
 * @param onSkip Action pour passer à la session suivante
 */
@Composable
private fun PomodoroControls(
    isRunning: Boolean,
    onStartPause: () -> Unit,
    onReset: () -> Unit,
    onSkip: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        // Bouton réinitialiser
        IconButton(
            onClick = onReset,
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(Color(0xFF121212))
        ) {
            Icon(
                Icons.Default.Refresh,
                contentDescription = "Réinitialiser",
                tint = Color.White
            )
        }

        // Bouton démarrer/pause
        IconButton(
            onClick = onStartPause,
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .background(if (isRunning) AccentColor else PrimaryColor)
        ) {
            Icon(
                if (isRunning) Icons.Default.Pause else Icons.Default.PlayArrow,
                contentDescription = if (isRunning) "Pause" else "Démarrer",
                tint = Color.White,
                modifier = Modifier.size(36.dp)
            )
        }

        // Bouton passer
        IconButton(
            onClick = onSkip,
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(Color(0xFF121212))
        ) {
            Icon(
                Icons.Default.SkipNext,
                contentDescription = "Passer",
                tint = Color.White
            )
        }
    }
}

/**
 * Carte des paramètres du mode Pomodoro
 * 
 * @param dndEnabled État du mode Ne pas déranger
 * @param notificationBlocked État du blocage des notifications
 * @param onDndToggle Action pour activer/désactiver le mode Ne pas déranger
 * @param onNotificationBlockToggle Action pour activer/désactiver le blocage des notifications
 */
@Composable
private fun PomodoroSettings(
    dndEnabled: Boolean,
    notificationBlocked: Boolean,
    onDndToggle: () -> Unit,
    onNotificationBlockToggle: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF121212))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Option Ne pas déranger
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        "Mode Ne pas déranger",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                    Text(
                        "Active le mode Ne pas déranger du système",
                        style = MaterialTheme.typography.bodySmall,
                        color = SecondaryColor.copy(alpha = 0.7f)
                    )
                }
                Switch(
                    checked = dndEnabled,
                    onCheckedChange = { onDndToggle() },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = PrimaryColor,
                        checkedTrackColor = PrimaryColor.copy(alpha = 0.5f),
                        uncheckedThumbColor = TextColor,
                        uncheckedTrackColor = TextColor.copy(alpha = 0.2f)
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

/**
 * Carte des statistiques du mode Pomodoro
 * 
 * @param completedSessions Nombre de sessions terminées
 * @param totalFocusMinutes Nombre total de minutes de focus
 * @param focusRate Taux de concentration en pourcentage
 */
@Composable
private fun PomodoroStatistics(
    completedSessions: Int,
    totalFocusMinutes: Int,
    focusRate: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF121212)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                "Statistiques",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Affichage des différentes métriques
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatisticItem(
                    title = "Sessions",
                    value = "$completedSessions"
                )
                StatisticItem(
                    title = "Minutes Focus",
                    value = "$totalFocusMinutes"
                )
                StatisticItem(
                    title = "Taux Focus",
                    value = "$focusRate%"
                )
            }
        }
    }
}

/**
 * Élément individuel de statistique
 * 
 * @param title Titre de la statistique
 * @param value Valeur de la statistique
 */
@Composable
private fun StatisticItem(
    title: String,
    value: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.displayLarge.copy(
                fontWeight = FontWeight.Light,
                fontSize = 64.sp
            ),
            color = PrimaryColor
        )
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFF959595)
        )
    }
}