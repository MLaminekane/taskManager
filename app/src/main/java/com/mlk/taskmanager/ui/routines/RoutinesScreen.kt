package com.mlk.taskmanager.ui.routines

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import com.mlk.taskmanager.ui.theme.Background
import com.mlk.taskmanager.ui.theme.TextColor
import com.mlk.taskmanager.ui.theme.PrimaryColor
import com.mlk.taskmanager.ui.theme.SecondaryColor
import com.mlk.taskmanager.ui.theme.AccentColor
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mlk.taskmanager.data.model.Routine
import com.mlk.taskmanager.ui.navigation.Screen
import java.time.DayOfWeek
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoutinesScreen(
    navController: NavController,
    viewModel: RoutinesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier.background(Background)
            ) {
                TopAppBar(
                    title = {
                        Text(
                            text = "My Routines",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = TextColor
                            )
                        )
                    },
                    actions = {
                        IconButton(onClick = { /* TODO: Filter routines */ }) {
                            Icon(Icons.Default.FilterList, contentDescription = "Filter", tint = TextColor)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Background,
                        titleContentColor = TextColor
                    )
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.AddRoutine.route) },
                modifier = Modifier.padding(16.dp),
                containerColor = PrimaryColor,
                contentColor = TextColor
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Routine")
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
                .padding(padding)
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else if (uiState.routines.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = null,
                        modifier = Modifier.size(72.dp),
                        tint = SecondaryColor.copy(alpha = 0.5f)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "No routines yet",
                        style = MaterialTheme.typography.headlineSmall,
                        color = TextColor
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Create a routine to get started",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = { navController.navigate(Screen.AddRoutine.route) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PrimaryColor,
                            contentColor = TextColor
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Add Routine")
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(vertical = 16.dp)
                ) {
                    items(uiState.routines) { routine ->
                        RoutineCard(
                            routine = routine,
                            onToggleEnabled = { viewModel.toggleRoutineEnabled(routine) },
                            onClick = { 
                                navController.navigate(Screen.RoutineDetail.createRoute(routine.id)) 
                            }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoutineCard(
    routine: Routine,
    onToggleEnabled: () -> Unit,
    onClick: () -> Unit
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (routine.isEnabled) Color.White else Color(0xFFF5F5F5),
        label = "background_color"
    )
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF121212)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Time column
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .width(64.dp)
                    .padding(end = 16.dp)
            ) {
                Text(
                    text = routine.time.format(DateTimeFormatter.ofPattern("h:mm")),
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = if (routine.isEnabled) PrimaryColor else SecondaryColor
                )
                
                Text(
                    text = routine.time.format(DateTimeFormatter.ofPattern("a")),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
            
            // Divider
            Divider(
                modifier = Modifier
                    .height(50.dp)
                    .width(1.dp),
                color = SecondaryColor.copy(alpha = 0.2f)
            )
            
            // Content column
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = routine.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = if (routine.isEnabled) TextColor else SecondaryColor
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                if (routine.description.isNotBlank()) {
                    Text(
                        text = routine.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = SecondaryColor,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                }
                
                // Days of week
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    DayOfWeek.values().forEach { day ->
                        val isSelected = routine.repeatDays.contains(day)
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(
                                    if (isSelected && routine.isEnabled) PrimaryColor.copy(alpha = 0.2f)
                                    else Color(0xFF222222)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = day.getDisplayName(TextStyle.NARROW, Locale.getDefault()),
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                ),
                                color = if (isSelected && routine.isEnabled) PrimaryColor else SecondaryColor
                            )
                        }
                    }
                }
            }
            
            // Switch
            Switch(
                checked = routine.isEnabled,
                onCheckedChange = { onToggleEnabled() },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = PrimaryColor,
                    checkedTrackColor = PrimaryColor.copy(alpha = 0.5f),
                    uncheckedThumbColor = TextColor,
                    uncheckedTrackColor = TextColor.copy(alpha = 0.2f)
                )
            )
        }
    }
} 