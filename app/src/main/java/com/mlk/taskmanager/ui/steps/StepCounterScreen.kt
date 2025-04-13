package com.mlk.taskmanager.ui.steps

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import com.mlk.taskmanager.ui.theme.Background
import com.mlk.taskmanager.ui.theme.TextColor
import com.mlk.taskmanager.ui.theme.PrimaryColor
import com.mlk.taskmanager.ui.theme.SecondaryColor
import com.mlk.taskmanager.ui.theme.AccentColor
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.DirectionsRun
import androidx.compose.material.icons.outlined.Pause
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.maps.model.Circle
import kotlinx.coroutines.delay
import java.text.DecimalFormat

/**
 * Écran de comptage de pas
 * Permet à l'utilisateur de suivre sa progression quotidienne en terme de pas
 * et de distance parcourue, avec intégration du gyroscope pour la détection 
 * des mouvements.
 *
 * @param navController Contrôleur de navigation
 * @param viewModel ViewModel de gestion du compteur de pas
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepCounterScreen(
    navController: NavController,
    viewModel: StepCounterViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    // Paramètres de progression
    val goalSteps = 10000
    val progressPercentage = (uiState.steps.toFloat() / goalSteps).coerceIn(0f, 1f)
    val animatedProgress by animateFloatAsState(
        targetValue = progressPercentage,
        label = "ProgressAnimation"
    )

    // Calcul basique des calories brûlées
    val calories = (uiState.steps * 0.04).toInt()

    // Mise à jour périodique du compteur de pas
    LaunchedEffect(Unit) {
        while (true) {
            viewModel.updateStepCount()
            delay(1000)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Mode Step",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = TextColor
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Retour", tint = TextColor)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Background,
                    titleContentColor = TextColor
                )
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(modifier = Modifier.padding(bottom = 24.dp)) {
                    Text(
                        text = "Activité Quotidienne",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = TextColor
                        )
                    )
                    Text(
                        text = "Suivez vos pas et votre activité",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = SecondaryColor
                        )
                    )
                }

                // Carte principale avec compteur de pas et progression
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    shape = RoundedCornerShape(24.dp),
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // En-tête de la carte
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFF121212))
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Icône activité
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(PrimaryColor.copy(alpha = 0.1f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.DirectionsRun,
                                        contentDescription = "Marche",
                                        tint = PrimaryColor,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                        }
                        
                        // Compteur de pas central
                        Text(
                            text = "${uiState.steps}",
                            style = MaterialTheme.typography.displayLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = White
                            ),
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        
                        // Objectif quotidien
                        Text(
                            text = "Objectif : $goalSteps pas",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = Color(0xFF64748B)
                            ),
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        
                        // Barre de progression
                        LinearProgressIndicator(
                            progress = { animatedProgress },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            color = PrimaryColor,
                            trackColor = PrimaryColor.copy(alpha = 0.1f)
                        )
                        
                        // Pourcentage de progression
                        Text(
                            text = "${(progressPercentage * 100).toInt()}%",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = PrimaryColor
                            ),
                            modifier = Modifier.padding(top = 8.dp, bottom = 24.dp)
                        )
                        
                        // Métriques supplémentaires : distance et calories
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // Métrique de distance
                            ElevatedCard(
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(24.dp),
                                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(
                                        text = "Distance",
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            color = Color(0xFF64748B)
                                        )
                                    )
                                    Row(
                                        modifier = Modifier.padding(top = 4.dp),
                                        verticalAlignment = Alignment.Bottom
                                    ) {
                                        Text(
                                            text = DecimalFormat("#.##").format(uiState.distance / 1000),
                                            style = MaterialTheme.typography.headlineMedium.copy(
                                                fontWeight = FontWeight.Bold,
                                                color = White
                                            )
                                        )
                                        Text(
                                            text = " km",
                                            style = MaterialTheme.typography.bodyMedium.copy(
                                                color = Color(0xFF475569)
                                            ),
                                            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
                                        )
                                    }
                                }
                            }
                            
                            Spacer(modifier = Modifier.width(16.dp))
                            
                            // Métrique de calories
                            ElevatedCard(
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(24.dp),
                                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(
                                        text = "Calories",
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            color = Color(0xFF64748B)
                                        )
                                    )
                                    Row(
                                        modifier = Modifier.padding(top = 4.dp),
                                        verticalAlignment = Alignment.Bottom
                                    ) {
                                        Text(
                                            text = "$calories",
                                            style = MaterialTheme.typography.headlineMedium.copy(
                                                fontWeight = FontWeight.Bold,
                                                color = White
                                            )
                                        )
                                        Text(
                                            text = " kcal",
                                            style = MaterialTheme.typography.bodyMedium.copy(
                                                color = Color(0xFF475569)
                                            ),
                                            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // Bouton Démarrer/Arrêter pour activer la détection des pas
                Button(
                    onClick = { 
                        if (uiState.isTracking) {
                            viewModel.stopTracking()
                        } else {
                            viewModel.startTracking()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (uiState.isTracking) 
                            AccentColor 
                        else 
                            PrimaryColor
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = if (uiState.isTracking) 
                                Icons.Outlined.Pause 
                            else 
                                Icons.Outlined.PlayArrow,
                            contentDescription = if (uiState.isTracking) "Arrêter" else "Démarrer",
                            modifier = Modifier.size(24.dp),
                            tint = White
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (uiState.isTracking) "Arrêter" else "Démarrer",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Medium,
                                color = White
                            )
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))

                // Bouton pour réinitialiser le compteur
                OutlinedButton(
                    onClick = { viewModel.resetCounter() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.outlinedButtonColors()
                ) {
                    Text(
                        "Réinitialiser",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Medium,
                            color = White
                        )
                    )
                }

                // Affichage des messages d'erreur
                if (uiState.error != null) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = uiState.error!!,
                        color = Color(0xFFDC2626),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                
                // Message d'état du détecteur
                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.elevatedCardColors(containerColor = Color(0xFF121212))
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = if (uiState.isTracking) 
                                "Détection des pas active" 
                            else 
                                "Détection des pas inactive",
                            style = MaterialTheme.typography.bodyMedium,
                            color = if (uiState.isTracking) 
                                PrimaryColor 
                            else 
                                SecondaryColor
                        )
                        Text(
                            text = if (uiState.isTracking) 
                                "Le gyroscope détecte vos mouvements" 
                            else 
                                "Appuyez sur Démarrer pour activer la détection",
                            style = MaterialTheme.typography.bodySmall,
                            color = SecondaryColor.copy(alpha = 0.7f),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }
        }
    }
}