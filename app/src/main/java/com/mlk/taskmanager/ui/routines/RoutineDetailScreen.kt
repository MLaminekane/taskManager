package com.mlk.taskmanager.ui.routines

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import com.mlk.taskmanager.data.model.Routine
import com.mlk.taskmanager.ui.navigation.Screen
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoutineDetailScreen(
    routineId: Long,
    navController: NavController,
    viewModel: RoutinesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val routine = uiState.routines.find { it.id == routineId }
    
    val scrollState = rememberScrollState()
    var showDeleteDialog by remember { mutableStateOf(false) }
    
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Delete Routine") },
            text = { Text("Are you sure you want to delete this routine? This action cannot be undone.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        routine?.let { viewModel.deleteRoutine(it) }
                        showDeleteDialog = false
                        navController.popBackStack()
                    }
                ) {
                    Text("Delete", color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Routine Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { showDeleteDialog = true }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete Routine"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // TODO: Navigate to edit routine screen
                },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Default.Edit, contentDescription = "Edit Routine")
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (routine == null) {
                // Routine not found state
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Routine not found",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { navController.navigate(Screen.Tasks.route) }) {
                        Text("Go back to routines")
                    }
                }
            } else {
                // Routine detail content
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    // Title
                    Text(
                        text = routine.title,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    // Status badge
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .background(
                                    if (routine.isEnabled) Color(0xFFE6F4EA) else Color(0xFFFCE8E6)
                                )
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = if (routine.isEnabled) "Active" else "Inactive",
                                color = if (routine.isEnabled) Color(0xFF137333) else Color(0xFFC5221F),
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }
                        
                        Spacer(modifier = Modifier.width(12.dp))
                        
                        // Toggle switch
                        Switch(
                            checked = routine.isEnabled,
                            onCheckedChange = { viewModel.toggleRoutineEnabled(routine) },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = MaterialTheme.colorScheme.primary,
                                checkedTrackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                            )
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    // Time
                    DetailItem(
                        icon = Icons.Default.Schedule,
                        label = "Time",
                        value = routine.time.format(DateTimeFormatter.ofPattern("h:mm a"))
                    )
                    
                    // Repeat days section
                    Text(
                        text = "Repeats on",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(top = 24.dp, bottom = 12.dp)
                    )
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        val dayLabels = listOf("M", "T", "W", "T", "F", "S", "S")
                        val days = listOf(
                            java.time.DayOfWeek.MONDAY,
                            java.time.DayOfWeek.TUESDAY,
                            java.time.DayOfWeek.WEDNESDAY,
                            java.time.DayOfWeek.THURSDAY,
                            java.time.DayOfWeek.FRIDAY,
                            java.time.DayOfWeek.SATURDAY,
                            java.time.DayOfWeek.SUNDAY
                        )
                        
                        days.forEachIndexed { index, day ->
                            val isSelected = routine.repeatDays.contains(day)
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = dayLabels[index],
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Gray
                                )
                                
                                Spacer(modifier = Modifier.height(8.dp))
                                
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(CircleShape)
                                        .background(
                                            if (isSelected) MaterialTheme.colorScheme.primary
                                            else Color.LightGray.copy(alpha = 0.3f)
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = day.getDisplayName(TextStyle.NARROW, Locale.getDefault()),
                                        color = if (isSelected) Color.White else Color.Gray,
                                        style = MaterialTheme.typography.bodyLarge.copy(
                                            fontWeight = FontWeight.Medium
                                        )
                                    )
                                }
                            }
                        }
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
                        text = routine.description.ifEmpty { "No description provided" },
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    // Location section (if present)
                    if (routine.latitude != null && routine.longitude != null) {
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
                                position = LatLng(routine.latitude, routine.longitude)
                            )
                            val cameraPositionState = rememberCameraPositionState {
                                position = CameraPosition.fromLatLngZoom(
                                    LatLng(routine.latitude, routine.longitude), 15f
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
                                    title = routine.title
                                )
                                
                                if (routine.locationRadius != null) {
                                    Circle(
                                        center = LatLng(routine.latitude, routine.longitude),
                                        radius = routine.locationRadius.toDouble(),
                                        fillColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                                        strokeColor = MaterialTheme.colorScheme.primary,
                                        strokeWidth = 2f
                                    )
                                }
                            }
                        }
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