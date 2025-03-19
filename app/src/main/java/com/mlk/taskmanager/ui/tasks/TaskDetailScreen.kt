package com.mlk.taskmanager.ui.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.mlk.taskmanager.data.model.Priority
import com.mlk.taskmanager.data.model.Task
import com.mlk.taskmanager.ui.navigation.Screen
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    taskId: Long,
    navController: NavController,
    viewModel: TasksViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val task = uiState.tasks.find { it.id == taskId }
    
    val scrollState = rememberScrollState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Task Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        task?.let { viewModel.deleteTask(it) }
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete Task"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // TODO: Navigate to edit task screen
                },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Default.Edit, contentDescription = "Edit Task")
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (task == null) {
                // Task not found state
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Task not found",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { navController.navigate(Screen.Tasks.route) }) {
                        Text("Go back to tasks")
                    }
                }
            } else {
                // Task detail content
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    // Priority indicator
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        val priorityColor = when (task.priority) {
                            Priority.LOW -> Color(0xFF4CAF50)
                            Priority.MEDIUM -> Color(0xFFFFC107)
                            Priority.HIGH -> Color(0xFFF44336)
                        }
                        
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(priorityColor)
                        )
                        
                        Spacer(modifier = Modifier.width(8.dp))
                        
                        Text(
                            text = "${task.priority.name} PRIORITY",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    
                    // Task title
                    Text(
                        text = task.title,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Due date
                    DetailItem(
                        icon = Icons.Default.CalendarToday,
                        label = "Due Date",
                        value = task.dueDateTime.format(DateTimeFormatter.ofPattern("E, MMM d, yyyy"))
                    )
                    
                    // Due time
                    DetailItem(
                        icon = Icons.Default.Schedule,
                        label = "Time",
                        value = task.dueDateTime.format(DateTimeFormatter.ofPattern("h:mm a"))
                    )
                    
                    // Category (if present)
                    task.category?.let {
                        DetailItem(
                            icon = Icons.Default.Category,
                            label = "Category",
                            value = it
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    // Description section
                    Text(
                        text = "Description",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = task.description.ifEmpty { "No description provided" },
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    // Location section (if present)
                    if (task.latitude != null && task.longitude != null) {
                        Text(
                            text = "Location",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .clip(RoundedCornerShape(12.dp))
                        ) {
                            val markerState = rememberMarkerState(
                                position = LatLng(task.latitude, task.longitude)
                            )
                            val cameraPositionState = rememberCameraPositionState {
                                position = CameraPosition.fromLatLngZoom(
                                    LatLng(task.latitude, task.longitude), 15f
                                )
                            }
                            
                            GoogleMap(
                                modifier = Modifier.fillMaxSize(),
                                cameraPositionState = cameraPositionState,
                                properties = MapProperties(isMyLocationEnabled = false),
                                uiSettings = MapUiSettings(
                                    zoomControlsEnabled = false,
                                    myLocationButtonEnabled = false
                                )
                            ) {
                                Marker(
                                    state = markerState,
                                    title = task.title
                                )
                                
                                if (task.locationRadius != null) {
                                    Circle(
                                        center = LatLng(task.latitude, task.longitude),
                                        radius = task.locationRadius.toDouble(),
                                        fillColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                                        strokeColor = MaterialTheme.colorScheme.primary,
                                        strokeWidth = 2f
                                    )
                                }
                            }
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(32.dp))
                    
                    // Complete button
                    Button(
                        onClick = {
                            viewModel.toggleTaskCompletion(task)
                            navController.popBackStack()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (task.isCompleted) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(
                            text = if (task.isCompleted) "Mark as Incomplete" else "Mark as Complete",
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DetailItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp)
        )
        
        Spacer(modifier = Modifier.width(12.dp))
        
        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
} 