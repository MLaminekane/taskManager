package com.mlk.taskmanager.ui.home

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mlk.taskmanager.R
import com.mlk.taskmanager.data.model.Project
import com.mlk.taskmanager.data.model.Routine
import com.mlk.taskmanager.data.model.Task
import com.mlk.taskmanager.data.model.WeatherResponse
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*
import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.text.input.TextFieldValue
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import com.mlk.taskmanager.ui.navigation.Screen
import kotlinx.coroutines.launch
import com.mlk.taskmanager.data.model.Priority
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.Opacity
import androidx.compose.material.icons.filled.Water
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material.icons.filled.Loop
import androidx.compose.material.icons.filled.KeyboardDoubleArrowUp
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.contentColorFor
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material.icons.filled.Circle
import com.mlk.taskmanager.ui.home.HomeUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val currentWeatherData = uiState.weatherData
    val isWeatherLoading = uiState.weatherLoading
    val projects = uiState.projects
    val routines = uiState.todayRoutines
    val upcomingTasks = uiState.upcomingTasks
    val name = uiState.currentUser
    
    val scope = rememberCoroutineScope()
    
    // État pour le dialogue de création de projet
    val showCreateProjectDialog = uiState.showCreateProjectDialog
    
    if (showCreateProjectDialog) {
        CreateProjectDialog(
            onDismiss = { viewModel.hideCreateProjectDialog() },
            onConfirm = { name, description, icon ->
                viewModel.createProject(name, description, icon)
                viewModel.hideCreateProjectDialog()
            }
        )
    }
    
    // Animation pour l'entrée de page
    val fadeInAnimation = remember { Animatable(0f) }
    val slideUpAnimation = remember { Animatable(50f) }
    
    LaunchedEffect(key1 = true) {
        fadeInAnimation.animateTo(
            targetValue = 1f,
            animationSpec = tween(500)
        )
        slideUpAnimation.animateTo(
            targetValue = 0f,
            animationSpec = tween(500, easing = FastOutSlowInEasing)
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp) // Espace pour la barre de navigation
                .graphicsLayer {
                    alpha = fadeInAnimation.value
                    translationY = slideUpAnimation.value
                }
        ) {
            item {
                // Header avec fond dégradé
                HeaderSection(uiState = uiState, navController = navController, viewModel = viewModel)
                
                // Quick Actions
                QuickActionsRow(navController)
                
                // Résumé des tâches avec animation
                TaskSummarySection(uiState = uiState)
                
                // Section des projets avec animation
                ProjectsSection(
                    projects = projects,
                    onAddProject = { viewModel.showCreateProjectDialog() },
                    onProjectClick = { project -> 
                        navController.navigate(Screen.ProjectDetail.createRoute(project.id))
                    }
                )
                
                // Section routines avec animations
                RoutinesSection(
                    routines = routines,
                    onAddRoutine = { navController.navigate(Screen.AddRoutine.route) },
                    onRoutineClick = { routine -> 
                        navController.navigate(Screen.RoutineDetail.createRoute(routine.id))
                    },
                    onSeeAllClick = { navController.navigate(Screen.Routines.route) }
                )
                
                // Section tâches à venir avec animations
                UpcomingTasksSection(
                    tasks = upcomingTasks,
                    onTaskClick = { task ->
                        navController.navigate(Screen.TaskDetail.createRoute(task.id))
                    },
                    onAddTaskClick = { navController.navigate(Screen.AddTask.route) }
                )
                
                // Espace en bas pour la navigation
                Spacer(modifier = Modifier.height(80.dp))
            }
            
            // Reste des éléments...
        }
    }
}

@Composable
fun HeaderSection(
    uiState: HomeUiState,
    navController: NavController,
    viewModel: HomeViewModel
) {
    // Obtenir l'heure locale pour ajuster le message de bienvenue
    val hour = java.time.LocalTime.now().hour
    val greeting = when {
        hour < 12 -> "Bonjour"
        hour < 18 -> "Bon après-midi"
        else -> "Bonsoir"
    }
    val name = uiState.currentUser
    
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        // Fond dégradé avec coins arrondis uniquement en bas
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.primaryContainer
                        )
                    )
                )
        )
        
        // Contenu
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Ligne supérieure avec salutation et avatar/profil
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Salutation
                Column {
                    Text(
                        text = "$greeting,",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                    Text(
                        text = name,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.White
                    )
                }
                
                // Avatar/Photo de profil (cliquable pour naviguer vers le profil)
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.2f))
                        .clickable { navController.navigate(Screen.Settings.route) },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profile",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Date actuelle avec animation de couleur
            Text(
                text = LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy", Locale.FRENCH)),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White.copy(alpha = 0.8f)
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Carte météo avec animation lors du clic
            WeatherCard(
                isLoading = uiState.weatherLoading,
                weatherData = uiState.weatherData,
                onClick = { /* Naviguer vers la météo détaillée si nécessaire */ }
            )
        }
    }
}

@Composable
fun WeatherCard(
    isLoading: Boolean,
    weatherData: WeatherResponse?,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
            .height(100.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        } else if (weatherData != null) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Température
                Text(
                    text = "${weatherData.main.temp.toInt()}°C",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White
                )
                
                Spacer(modifier = Modifier.width(16.dp))
                
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    // Nom de la localisation
                    Text(
                        text = weatherData.name,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.White
                    )
                    
                    // Description de la météo
                    val weatherDesc = if (weatherData.weather.isNotEmpty()) {
                        weatherData.weather[0].description
                    } else {
                        ""
                    }
                    Text(
                        text = weatherDesc,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.7f)
                    )
                }
                
                // Icône météo
                val weatherType = if (weatherData.weather.isNotEmpty()) {
                    weatherData.weather[0].main
                } else {
                    ""
                }
                Icon(
                    painter = getWeatherIcon(weatherType),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(48.dp)
                )
            }
        } else {
            // État par défaut quand les données ne sont pas disponibles
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.WbSunny,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Météo non disponible",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun QuickActionsRow(navController: NavController) {
    val actionItems = listOf(
        ActionItem("Tâches", Icons.Filled.Assignment) { navController.navigate(Screen.Tasks.route) },
        ActionItem("Routines", Icons.Filled.Loop) { navController.navigate(Screen.Routines.route) },
        ActionItem("Pomodoro", Icons.Filled.Timer) { navController.navigate(Screen.Pomodoro.route) },
        ActionItem("Pas", Icons.Filled.DirectionsWalk) { navController.navigate(Screen.StepCounter.route) }
    )
    
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(actionItems) { item ->
            QuickActionItem(item)
        }
    }
}

data class ActionItem(
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val onClick: () -> Unit
)

@Composable
fun QuickActionItem(item: ActionItem) {
    val scale = remember { Animatable(1f) }
    val scope = rememberCoroutineScope()
    
    Column(
        modifier = Modifier
            .graphicsLayer {
                scaleX = scale.value
                scaleY = scale.value
            }
            .clickable {
                scope.launch {
                    scale.animateTo(0.8f, tween(100))
                    scale.animateTo(1f, spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    ))
                    item.onClick()
                }
            }
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.7f))
            .padding(vertical = 12.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = item.title,
            tint = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = item.title,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}

@Composable
private fun SummaryCard(
    title: String,
    count: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray
                )
            )
            Text(
                text = count.toString(),
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 40.sp
                )
            )
        }
    }
}

@Composable
private fun ProjectCard(
    project: Project,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .height(160.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = Color(project.color).copy(alpha = 0.1f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = when (project.icon) {
                    "kotlin" -> Icons.Default.Code
                    "typescript" -> Icons.Default.Web
                    else -> Icons.Default.Folder
                },
                contentDescription = null,
                tint = Color(project.color),
                modifier = Modifier.size(32.dp)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = project.name,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            
            Text(
                text = project.description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Assignment,
                    contentDescription = null,
                    tint = Color(project.color),
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${project.taskCount} tasks",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(project.color)
                )
            }
        }
    }
}

@Composable
private fun TaskCard(
    task: Task,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = task.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = task.description,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray
                ),
                maxLines = 2
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Schedule,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = task.dueDateTime.format(
                            DateTimeFormatter.ofPattern("MMM dd, HH:mm")
                        ),
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color.Gray
                        )
                    )
                }
                
                Surface(
                    modifier = Modifier.clip(RoundedCornerShape(16.dp)),
                    color = if (task.isCompleted) 
                        Color(0xFF4CAF50).copy(alpha = 0.1f)
                    else 
                        Color(0xFF613BE7).copy(alpha = 0.1f)
                ) {
                    Text(
                        text = if (task.isCompleted) "Completed" else "In Progress",
                        color = if (task.isCompleted) 
                            Color(0xFF4CAF50)
                        else 
                            Color(0xFF613BE7),
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun EmptyTasksMessage() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Assignment,
            contentDescription = null,
            tint = Color(0xFF613BE7),
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "No tasks for today",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = "Add a new task by tapping the + button",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CreateProjectDialog(
    onDismiss: () -> Unit,
    onConfirm: (name: String, description: String, icon: String) -> Unit
) {
    var projectName by remember { mutableStateOf(TextFieldValue()) }
    var projectDescription by remember { mutableStateOf(TextFieldValue()) }
    var selectedIcon by remember { mutableStateOf("folder") }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        // Handle image selection if needed
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Create New Project",
                style = MaterialTheme.typography.titleLarge
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = projectName,
                    onValueChange = { projectName = it },
                    label = { Text("Project Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = projectDescription,
                    onValueChange = { projectDescription = it },
                    label = { Text("Description") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3
                )

                Text(
                    text = "Project Icon",
                    style = MaterialTheme.typography.titleMedium
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val icons = listOf(
                        "kotlin" to Icons.Default.Code,
                        "typescript" to Icons.Default.Web,
                        "folder" to Icons.Default.Folder
                    )

                    items(icons) { (type, icon) ->
                        IconButton(
                            onClick = { selectedIcon = type },
                            modifier = Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(
                                    if (selectedIcon == type)
                                        Color(0xFF613BE7).copy(alpha = 0.1f)
                                    else
                                        Color.Transparent
                                )
                                .border(
                                    width = 1.dp,
                                    color = if (selectedIcon == type)
                                        Color(0xFF613BE7)
                                    else
                                        Color.Gray,
                                    shape = RoundedCornerShape(8.dp)
                                )
                        ) {
                            Icon(
                                imageVector = icon,
                                contentDescription = type,
                                tint = if (selectedIcon == type)
                                    Color(0xFF613BE7)
                                else
                                    Color.Gray
                            )
                        }
                    }

                    item {
                        IconButton(
                            onClick = { 
                                imagePickerLauncher.launch("image/*")
                            },
                            modifier = Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.Transparent)
                                .border(
                                    width = 1.dp,
                                    color = Color.Gray,
                                    shape = RoundedCornerShape(8.dp)
                                )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Custom Icon",
                                tint = Color.Gray
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (projectName.text.isNotBlank()) {
                        onConfirm(
                            projectName.text,
                            projectDescription.text,
                            selectedIcon
                        )
                        onDismiss()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add, 
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
private fun ProjectTypeSelector(
    selectedType: String,
    onTypeSelected: (String) -> Unit
) {
    val types = listOf(
        Triple("Travail", "work", MaterialTheme.colorScheme.error),
        Triple("Personnel", "home", MaterialTheme.colorScheme.secondary),
        Triple("Études", "study", MaterialTheme.colorScheme.primary),
        Triple("Projet", "project", MaterialTheme.colorScheme.tertiary)
    )
    
    // Utiliser des icônes standard Material Design
    val icons = mapOf(
        "work" to Icons.Default.Work,
        "home" to Icons.Default.Home,
        "study" to Icons.Default.School,
        "project" to Icons.Default.Description
    )
    
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(types) { (label, type, color) ->
            IconButton(
                onClick = { onTypeSelected(type) },
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        if (selectedType == type)
                            color.copy(alpha = 0.1f)
                        else
                            Color.Transparent
                    )
                    .border(
                        width = 1.dp,
                        color = if (selectedType == type)
                            color
                        else
                            Color.Gray,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Icon(
                    imageVector = icons[type]!!,
                    contentDescription = type,
                    tint = if (selectedType == type)
                        color
                    else
                        Color.Gray
                )
            }
        }
    }
}

@Composable
private fun RoutineCardCompact(
    routine: com.mlk.taskmanager.data.model.Routine,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = if (routine.isEnabled) Color.White else Color(0xFFF5F5F5)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Time
            Text(
                text = routine.time.format(DateTimeFormatter.ofPattern("h:mm a")),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = if (routine.isEnabled) MaterialTheme.colorScheme.primary else Color.Gray
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            // Days
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                val days = listOf("M", "T", "W", "T", "F", "S", "S")
                val dayValues = listOf(
                    DayOfWeek.MONDAY,
                    DayOfWeek.TUESDAY,
                    DayOfWeek.WEDNESDAY,
                    DayOfWeek.THURSDAY,
                    DayOfWeek.FRIDAY,
                    DayOfWeek.SATURDAY,
                    DayOfWeek.SUNDAY
                )
                
                dayValues.forEachIndexed { index, day ->
                    val isSelected = routine.repeatDays.contains(day)
                    Box(
                        modifier = Modifier
                            .size(18.dp)
                            .clip(CircleShape)
                            .background(
                                if (isSelected && routine.isEnabled) MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                                else Color.LightGray.copy(alpha = 0.3f)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = days[index],
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            ),
                            color = if (isSelected && routine.isEnabled) MaterialTheme.colorScheme.primary else Color.Gray
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Title
            Text(
                text = routine.title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Medium
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            // Status indicator
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(if (routine.isEnabled) Color(0xFF4CAF50) else Color.Gray)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = if (routine.isEnabled) "Active" else "Inactive",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
private fun WeatherInfoItem(label: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
fun TaskSummarySection(uiState: HomeUiState) {
    // Animation d'entrée (slide in de droite et gauche)
    val animatedProgress = remember { Animatable(0f) }
    
    LaunchedEffect(key1 = true) {
        animatedProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(800, easing = FastOutSlowInEasing)
        )
    }
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Text(
            text = "Votre progression",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Tâches assignées
            AnimatedSummaryCard(
                title = "Tâches assignées",
                count = uiState.assignedTasks,
                icon = Icons.Filled.Assignment,
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                animatedProgress = animatedProgress,
                animationOffsetX = -100f, // Animation de gauche à droite
                modifier = Modifier.weight(1f)
            )
            
            // Tâches complétées
            AnimatedSummaryCard(
                title = "Tâches terminées",
                count = uiState.completedTasks,
                icon = Icons.Filled.Check,
                backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                animatedProgress = animatedProgress,
                animationOffsetX = 100f, // Animation de droite à gauche
                modifier = Modifier.weight(1f)
            )
        }
        
        // Barre de progression globale
        if (uiState.assignedTasks > 0) {
            val progress = if (uiState.assignedTasks > 0) {
                uiState.completedTasks.toFloat() / uiState.assignedTasks.toFloat()
            } else 0f
            
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Progression globale",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "${(progress * 100).toInt()}%",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Barre de progression animée
                val animatedProgress = remember(progress) {
                    Animatable(0f)
                }
                
                LaunchedEffect(progress) {
                    animatedProgress.snapTo(0f)
                    animatedProgress.animateTo(
                        targetValue = progress,
                        animationSpec = tween(800, easing = FastOutSlowInEasing)
                    )
                }
                
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(animatedProgress.value)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(4.dp))
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        MaterialTheme.colorScheme.primary,
                                        MaterialTheme.colorScheme.tertiary
                                    )
                                )
                            )
                    )
                }
            }
        }
    }
}

@Composable
fun AnimatedSummaryCard(
    title: String,
    count: Int,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    backgroundColor: Color,
    contentColor: Color,
    animatedProgress: Animatable<Float, AnimationVector1D>,
    animationOffsetX: Float,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .graphicsLayer {
                alpha = animatedProgress.value
                translationX = (1f - animatedProgress.value) * animationOffsetX
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(contentColor.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = contentColor,
                    modifier = Modifier.size(24.dp)
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = count.toString(),
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = contentColor
            )
            
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = contentColor.copy(alpha = 0.8f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun ProjectsSection(
    projects: List<Project>,
    onAddProject: () -> Unit,
    onProjectClick: (Project) -> Unit
) {
    val fadeIn = remember { Animatable(0f) }
    val slideUp = remember { Animatable(50f) }
    
    LaunchedEffect(key1 = true) {
        fadeIn.animateTo(
            targetValue = 1f,
            animationSpec = tween(800, delayMillis = 300)
        )
        slideUp.animateTo(
            targetValue = 0f,
            animationSpec = tween(800, delayMillis = 300, easing = FastOutSlowInEasing)
        )
    }
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 8.dp)
            .graphicsLayer {
                alpha = fadeIn.value
                translationY = slideUp.value
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Vos projets",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            
            FloatingActionButton(
                onClick = onAddProject,
                modifier = Modifier.size(42.dp),
                shape = CircleShape,
                elevation = FloatingActionButtonDefaults.elevation(4.dp),
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Ajouter un projet",
                    modifier = Modifier.size(18.dp)
                )
            }
        }
        
        AnimatedVisibility(
            visible = true,
            enter = fadeIn() + expandVertically()
        ) {
            if (projects.isEmpty()) {
                // Afficher un message s'il n'y a pas de projets
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Folder,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(48.dp)
                        )
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Text(
                            text = "Aucun projet",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        
                        Text(
                            text = "Créez votre premier projet pour organiser vos tâches",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            } else {
                // Afficher la liste des projets
                Spacer(modifier = Modifier.height(16.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(projects) { project ->
                        // Utiliser notre card redesignée pour les projets
                        ImprovedProjectCard(
                            project = project,
                            onClick = { onProjectClick(project) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ImprovedProjectCard(
    project: Project,
    onClick: () -> Unit
) {
    val cardScale = remember { Animatable(1f) }
    val cardElevation = remember { Animatable(4f) }
    val scope = rememberCoroutineScope()
    
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        // Fond d'origine - préservé
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f)
            ),
            elevation = CardDefaults.cardElevation(0.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                // Contenu vide pour le fond
            }
        }
        
        // Carte du projet superposée
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .padding(2.dp)
                .graphicsLayer {
                    scaleX = cardScale.value
                    scaleY = cardScale.value
                    shadowElevation = cardElevation.value
                }
                .clickable {
                    scope.launch {
                        // Animation de clic
                        launch {
                            cardScale.animateTo(0.95f, tween(100))
                            cardScale.animateTo(1f, spring(
                                dampingRatio = Spring.DampingRatioMediumBouncy,
                                stiffness = Spring.StiffnessLow
                            ))
                        }
                        launch {
                            cardElevation.animateTo(8f, tween(100))
                            cardElevation.animateTo(4f, tween(200))
                        }
                        onClick()
                    }
                },
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(project.color).copy(alpha = 0.2f) 
            ),
            elevation = CardDefaults.cardElevation(0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Icône du projet
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            MaterialTheme.colorScheme.primaryContainer
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = when (project.icon) {
                            "work" -> Icons.Default.Work
                            "home" -> Icons.Default.Home
                            "study" -> Icons.Default.School
                            else -> Icons.Default.Description
                        },
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.size(24.dp)
                    )
                }
                
                Spacer(modifier = Modifier.height(12.dp))
                
                // Titre du projet
                Text(
                    text = project.name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                
                // Description du projet
                Text(
                    text = project.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 4.dp)
                )
                
                Spacer(modifier = Modifier.weight(1f))
                
                // Barre de progression
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "0/${project.taskCount} tâches",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        
                        // Pourcentage de progression
                        val progressPercentage = if (project.taskCount > 0) {
                            (0f / project.taskCount.toFloat() * 100).toInt()
                        } else {
                            0
                        }
                        
                        Text(
                            text = "$progressPercentage%",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(4.dp))
                    
                    // Barre de progression animée
                    val progress = if (project.taskCount > 0) {
                        0f / project.taskCount.toFloat()
                    } else {
                        0f
                    }
                    
                    val animatedProgress = remember(progress) {
                        Animatable(0f)
                    }
                    
                    LaunchedEffect(progress) {
                        animatedProgress.animateTo(
                            targetValue = progress,
                            animationSpec = tween(800, easing = FastOutSlowInEasing)
                        )
                    }
                    
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(animatedProgress.value)
                                .fillMaxHeight()
                                .clip(RoundedCornerShape(3.dp))
                                .background(MaterialTheme.colorScheme.primary)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RoutinesSection(
    routines: List<Routine>,
    onAddRoutine: () -> Unit,
    onRoutineClick: (Routine) -> Unit,
    onSeeAllClick: () -> Unit
) {
    val fadeIn = remember { Animatable(0f) }
    val slideUp = remember { Animatable(50f) }
    
    LaunchedEffect(key1 = true) {
        fadeIn.animateTo(
            targetValue = 1f,
            animationSpec = tween(800, delayMillis = 400)
        )
        slideUp.animateTo(
            targetValue = 0f,
            animationSpec = tween(800, delayMillis = 400, easing = FastOutSlowInEasing)
        )
    }
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 24.dp)
            .graphicsLayer {
                alpha = fadeIn.value
                translationY = slideUp.value
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Routines du jour",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = onSeeAllClick
                ) {
                    Text(
                        text = "Voir tout",
                        color = MaterialTheme.colorScheme.primary
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                
                FloatingActionButton(
                    onClick = onAddRoutine,
                    modifier = Modifier.size(42.dp),
                    shape = CircleShape,
                    elevation = FloatingActionButtonDefaults.elevation(4.dp),
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Ajouter une routine",
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
        
        AnimatedVisibility(
            visible = true,
            enter = fadeIn() + expandVertically()
        ) {
            if (routines.isEmpty()) {
                // Afficher un message s'il n'y a pas de routines
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Loop,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(48.dp)
                        )
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Text(
                            text = "Aucune routine aujourd'hui",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        
                        Text(
                            text = "Créez des routines pour automatiser vos tâches récurrentes",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                            textAlign = TextAlign.Center
                        )
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Button(
                            onClick = onAddRoutine,
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Créer une routine")
                        }
                    }
                }
            } else {
                // Afficher la liste des routines
                Spacer(modifier = Modifier.height(16.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(routines) { routine ->
                        // Utiliser notre card redesignée pour les routines
                        ImprovedRoutineCard(
                            routine = routine,
                            onClick = { onRoutineClick(routine) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ImprovedRoutineCard(
    routine: Routine,
    onClick: () -> Unit
) {
    val cardScale = remember { Animatable(1f) }
    val cardElevation = remember { Animatable(4f) }
    val scope = rememberCoroutineScope()
    
    // Couleur basée sur le statut de la routine
    val backgroundColor = if (routine.isEnabled) {
        if (routine.isSyncedWithCalendar) {
            // Routine activée et synchronisée avec Google Calendar
            MaterialTheme.colorScheme.tertiaryContainer
        } else {
            // Routine activée mais pas synchronisée
            MaterialTheme.colorScheme.secondaryContainer
        }
    } else {
        // Routine désactivée
        MaterialTheme.colorScheme.surfaceVariant
    }
    
    val contentColor = if (routine.isEnabled) {
        if (routine.isSyncedWithCalendar) {
            MaterialTheme.colorScheme.onTertiaryContainer
        } else {
            MaterialTheme.colorScheme.onSecondaryContainer
        }
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
    }
    
    Card(
        modifier = Modifier
            .width(180.dp)
            .height(180.dp)
            .graphicsLayer {
                scaleX = cardScale.value
                scaleY = cardScale.value
                shadowElevation = cardElevation.value
            }
            .clickable {
                scope.launch {
                    // Animation de clic
                    launch {
                        cardScale.animateTo(0.95f, tween(100))
                        cardScale.animateTo(1f, spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        ))
                    }
                    launch {
                        cardElevation.animateTo(8f, tween(100))
                        cardElevation.animateTo(4f, tween(200))
                    }
                    onClick()
                }
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Statut de synchronisation
            if (routine.isSyncedWithCalendar) {
                Box(
                    modifier = Modifier
                        .align(Alignment.End)
                        .clip(RoundedCornerShape(4.dp))
                        .background(MaterialTheme.colorScheme.tertiary.copy(alpha = 0.2f))
                        .padding(horizontal = 6.dp, vertical = 3.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Sync,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.tertiary,
                            modifier = Modifier.size(12.dp)
                        )
                        Text(
                            text = "Synchronisé",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }
                }
            }
            
            // Heure de la routine
            Text(
                text = routine.time.format(java.time.format.DateTimeFormatter.ofPattern("HH:mm")),
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = contentColor
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Titre de la routine
            Text(
                text = routine.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = contentColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            
            // Description de la routine
            Text(
                text = routine.description,
                style = MaterialTheme.typography.bodySmall,
                color = contentColor.copy(alpha = 0.8f),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 4.dp)
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            // Jours de répétition
            if (routine.repeatDays.isNotEmpty()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    val daysOfWeek = DayOfWeek.values()
                    for (day in daysOfWeek) {
                        val isSelected = routine.repeatDays.contains(day)
                        val dayLabel = day.getDisplayName(TextStyle.NARROW, Locale.getDefault())
                        
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .clip(CircleShape)
                                .background(
                                    if (isSelected) contentColor
                                    else contentColor.copy(alpha = 0.1f)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = dayLabel,
                                style = MaterialTheme.typography.labelSmall,
                                color = if (isSelected) backgroundColor else contentColor,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun UpcomingTasksSection(
    tasks: List<Task>,
    onTaskClick: (Task) -> Unit,
    onAddTaskClick: () -> Unit
) {
    val fadeIn = remember { Animatable(0f) }
    val slideUp = remember { Animatable(50f) }
    
    LaunchedEffect(key1 = true) {
        fadeIn.animateTo(
            targetValue = 1f,
            animationSpec = tween(800, delayMillis = 500)
        )
        slideUp.animateTo(
            targetValue = 0f,
            animationSpec = tween(800, delayMillis = 500, easing = FastOutSlowInEasing)
        )
    }
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 24.dp)
            .graphicsLayer {
                alpha = fadeIn.value
                translationY = slideUp.value
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Tâches à venir",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            
            FloatingActionButton(
                onClick = onAddTaskClick,
                modifier = Modifier.size(42.dp),
                shape = CircleShape,
                elevation = FloatingActionButtonDefaults.elevation(4.dp),
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Ajouter une tâche",
                    modifier = Modifier.size(18.dp)
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Afficher les tâches
        AnimatedVisibility(
            visible = true,
            enter = fadeIn() + expandVertically()
        ) {
            if (tasks.isEmpty()) {
                // Afficher un message s'il n'y a pas de tâches
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.TaskAlt,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(48.dp)
                        )
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Text(
                            text = "Aucune tâche à venir",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        
                        Text(
                            text = "Créez des tâches pour organiser votre travail",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                            textAlign = TextAlign.Center
                        )
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Button(
                            onClick = onAddTaskClick,
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Créer une tâche")
                        }
                    }
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Limiter à 3 tâches pour ne pas surcharger l'écran d'accueil
                    tasks.take(3).forEach { task ->
                        AnimatedTaskCard(
                            task = task,
                            onClick = { onTaskClick(task) }
                        )
                    }
                    
                    // Bouton "Voir plus" si plus de 3 tâches
                    if (tasks.size > 3) {
                        TextButton(
                            onClick = { /* Navigation vers la liste complète */ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                        ) {
                            Text(
                                text = "Voir ${tasks.size - 3} tâches supplémentaires",
                                color = MaterialTheme.colorScheme.primary
                            )
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AnimatedTaskCard(
    task: Task,
    onClick: () -> Unit
) {
    val cardScale = remember { Animatable(1f) }
    val cardElevation = remember { Animatable(4f) }
    val scope = rememberCoroutineScope()
    
    // Couleur basée sur la priorité de la tâche
    val (backgroundColor, contentColor) = when (task.priority) {
        Priority.HIGH -> Pair(
            MaterialTheme.colorScheme.errorContainer.copy(alpha = if (task.isCompleted) 0.5f else 0.7f),
            MaterialTheme.colorScheme.onErrorContainer.copy(alpha = if (task.isCompleted) 0.7f else 1f)
        )
        Priority.MEDIUM -> Pair(
            MaterialTheme.colorScheme.secondaryContainer.copy(alpha = if (task.isCompleted) 0.5f else 0.7f),
            MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = if (task.isCompleted) 0.7f else 1f)
        )
        else -> Pair(
            MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = if (task.isCompleted) 0.5f else 0.7f),
            MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = if (task.isCompleted) 0.7f else 1f)
        )
    }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer {
                scaleX = cardScale.value
                scaleY = cardScale.value
                shadowElevation = cardElevation.value
            }
            .clickable {
                scope.launch {
                    // Animation de clic
                    launch {
                        cardScale.animateTo(0.98f, tween(100))
                        cardScale.animateTo(1f, spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        ))
                    }
                    launch {
                        cardElevation.animateTo(8f, tween(100))
                        cardElevation.animateTo(4f, tween(200))
                    }
                    onClick()
                }
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (task.isCompleted) 
                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f) 
            else 
                backgroundColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Checkbox ou icône de statut
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(
                        if (task.isCompleted) 
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
                        else 
                            contentColor.copy(alpha = 0.1f)
                    )
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                if (task.isCompleted) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Completed",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // Contenu de la tâche
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        textDecoration = if (task.isCompleted) TextDecoration.LineThrough else null
                    ),
                    color = if (task.isCompleted) 
                        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                    else 
                        contentColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                
                if (task.description.isNotEmpty()) {
                    Text(
                        text = task.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = if (task.isCompleted) 
                            MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                        else 
                            contentColor.copy(alpha = 0.7f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                
                // Date d'échéance
                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Schedule,
                        contentDescription = null,
                        tint = if (task.isCompleted) 
                            MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                        else 
                            contentColor.copy(alpha = 0.7f),
                        modifier = Modifier.size(14.dp)
                    )
                    
                    Spacer(modifier = Modifier.width(4.dp))
                    
                    val dateFormatter = DateTimeFormatter.ofPattern("d MMM, HH:mm")
                    Text(
                        text = task.dueDateTime.format(dateFormatter),
                        style = MaterialTheme.typography.labelSmall,
                        color = if (task.isCompleted) 
                            MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                        else 
                            contentColor.copy(alpha = 0.7f)
                    )
                    
                    // Statut de synchronisation
                    if (task.isSyncedWithCalendar) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Default.Sync,
                            contentDescription = "Synchronized with Google Calendar",
                            tint = if (task.isCompleted) 
                                MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                            else 
                                MaterialTheme.colorScheme.tertiary,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }
            }
            
            // Badge de priorité
            val priorityIcon = when (task.priority) {
                Priority.HIGH -> Icons.Default.KeyboardDoubleArrowUp
                Priority.MEDIUM -> Icons.Default.KeyboardArrowUp
                else -> Icons.Default.KeyboardArrowDown
            }
            
            val priorityColor = when (task.priority) {
                Priority.HIGH -> MaterialTheme.colorScheme.error
                Priority.MEDIUM -> MaterialTheme.colorScheme.secondary
                else -> MaterialTheme.colorScheme.tertiary
            }
            
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(priorityColor.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = priorityIcon,
                    contentDescription = "Priority",
                    tint = priorityColor.copy(alpha = if (task.isCompleted) 0.5f else 1f),
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

/**
 * Retourne l'icône météo correspondant à la condition météo
 */
@Composable
fun getWeatherIcon(condition: String): Painter {
    // Retourner l'icône appropriée en fonction de la condition météo
    return when (condition.lowercase()) {
        "clear" -> rememberVectorPainter(Icons.Default.WbSunny)
        "clouds" -> rememberVectorPainter(Icons.Default.Cloud)
        "rain" -> rememberVectorPainter(Icons.Default.Opacity)
        "snow" -> rememberVectorPainter(Icons.Default.AcUnit)
        "thunderstorm" -> rememberVectorPainter(Icons.Default.FlashOn)
        "mist", "fog", "haze" -> rememberVectorPainter(Icons.Default.Water)
        else -> rememberVectorPainter(Icons.Default.WbSunny)
    }
}