package com.mlk.taskmanager.ui.steps

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.DirectionsRun
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.maps.model.Circle
import kotlinx.coroutines.delay
import java.text.DecimalFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepCounterScreen(
    navController: NavController,
    viewModel: StepCounterViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val goalSteps = 10000
    val progressPercentage = (uiState.steps.toFloat() / goalSteps).coerceIn(0f, 1f)
    val animatedProgress by animateFloatAsState(
        targetValue = progressPercentage,
        label = "ProgressAnimation"
    )

    val calories = (uiState.steps * 0.04).toInt()

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
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Retour")
                    }
                }
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFFF8F9FA)
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
                            color = Color(0xFF1E293B)
                        )
                    )
                    Text(
                        text = "Suivez vos pas et votre activité",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color(0xFF64748B)
                        )
                    )
                }


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

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Progression",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xFF334155)
                                )
                            )
                            Text(
                                text = "${(progressPercentage * 100).toInt()}% de l'objectif",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = Color(0xFF64748B)
                                )
                            )
                        }

                        // Barre de progression
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(16.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFE2E8F0))
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(animatedProgress)
                                    .fillMaxHeight()
                                    .clip(CircleShape)
                                    .background(Color(0xFF3B82F6))
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))


                        Box(
                            modifier = Modifier.padding(vertical = 16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "${uiState.steps}",
                                    style = MaterialTheme.typography.displayLarge.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 56.sp,
                                        color = Color(0xFF1E293B)
                                    )
                                )
                                Text(
                                    text = "pas",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        color = Color(0xFF3B82F6),
                                        fontWeight = FontWeight.Medium
                                    )
                                )
                            }
                        }

                        Text(
                            text = "Objectif: $goalSteps pas",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color(0xFF64748B)
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {

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
                                        color = Color(0xFF1E293B)
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
                                        color = Color(0xFF1E293B)
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


                Button(
                    onClick = { viewModel.resetCounter() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        "Réinitialiser",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Medium
                        )
                    )
                }

                // Affichage des erreurs
                if (uiState.error != null) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = uiState.error!!,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}