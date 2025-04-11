package com.mlk.taskmanager.ui.tasks

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mlk.taskmanager.data.model.Priority
import com.mlk.taskmanager.data.model.Task
import com.mlk.taskmanager.ui.navigation.Screen
import com.mlk.taskmanager.ui.theme.Background
import com.mlk.taskmanager.ui.theme.TextColor
import com.mlk.taskmanager.ui.theme.PrimaryColor
import com.mlk.taskmanager.ui.theme.SecondaryColor
import com.mlk.taskmanager.ui.theme.AccentColor
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime
import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.foundation.clickable
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration

@OptIn(
    ExperimentalMaterial3Api::class,
    androidx.compose.material3.ExperimentalMaterial3Api::class
)
@Composable
fun TasksScreen(
    navController: NavController,
    viewModel: TasksViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedFilter by remember { mutableStateOf("All") }
    
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .background(Background)
                    .padding(top = 12.dp)
            ) {
                // Afficher la barre de recherche si la recherche est active
                if (uiState.isSearchActive) {
                    SearchBar(
                        query = uiState.searchQuery,
                        onQueryChange = { viewModel.setSearchQuery(it) },
                        onCloseSearch = { viewModel.toggleSearchActive() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                } else {
                    // Afficher la barre standard si la recherche n'est pas active
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "My Tasks",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.Bold,
                                letterSpacing = (-0.5).sp,
                                color = TextColor
                            )
                        )
                        
                        Row {
                            IconButton(
                                onClick = { viewModel.toggleSearchActive() },
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape)
                                    .background(Background.copy(alpha = 0.5f))
                            ) {
                                Icon(
                                    Icons.Default.Search, 
                                    contentDescription = "Search",
                                    tint = TextColor
                                )
                            }
                            
                            Spacer(modifier = Modifier.width(8.dp))
                            
                            IconButton(
                                onClick = { viewModel.toggleFilterDialog() },
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape)
                                    .background(Background.copy(alpha = 0.5f))
                            ) {
                                Icon(
                                    Icons.Default.FilterList, 
                                    contentDescription = "Filter",
                                    tint = TextColor
                                )
                            }
                        }
                    }
                }

                // Task filters - Style amélioré
                AnimatedVisibility(
                    visible = true,
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    ) {
                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(listOf("All", "Today", "In Progress", "Completed")) { filter ->
                                val isSelected = selectedFilter == filter
                                Card(
                                    modifier = Modifier
                                        .height(40.dp)
                                        .clickable { selectedFilter = filter }
                                        .animateContentSize(),
                                    colors = CardDefaults.cardColors(
                                        containerColor = if (isSelected)
                                            PrimaryColor
                                        else
                                            Background.copy(alpha = 0.7f)
                                    ),
                                    shape = RoundedCornerShape(20.dp),
                                    elevation = CardDefaults.cardElevation(
                                        defaultElevation = if (isSelected) 4.dp else 0.dp
                                    )
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .padding(horizontal = 20.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = filter,
                                            style = MaterialTheme.typography.labelLarge.copy(
                                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                                color = if (isSelected)
                                                    Color.White
                                                else
                                                    TextColor
                                            ),
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.AddTask.route) },
                containerColor = PrimaryColor,
                contentColor = Color.White,
                modifier = Modifier
                    .padding(16.dp)
                    .size(56.dp),
                shape = CircleShape,
                elevation = FloatingActionButtonDefaults.elevation(8.dp)
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Add Task",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Background.copy(alpha = 0.95f))
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = PrimaryColor,
                    strokeWidth = 3.dp
                )
            } else if (uiState.filteredTasks.isEmpty()) {
                EmptyTasksMessage(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                val displayTasks = when (selectedFilter) {
                    "Today" -> uiState.filteredTasks.filter {
                        it.dueDateTime.toLocalDate() == LocalDateTime.now().toLocalDate()
                    }
                    "Completed" -> uiState.filteredTasks.filter { it.isCompleted }
                    "In Progress" -> uiState.filteredTasks.filter { !it.isCompleted }
                    else -> uiState.filteredTasks
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(vertical = 16.dp)
                ) {
                    items(
                        items = displayTasks,
                        key = { it.id }
                    ) { task ->
                        TaskItem(
                            task = task,
                            onClick = { 
                                navController.navigate(Screen.TaskDetail.createRoute(task.id))
                            },
                            onCheckedChange = { 
                                viewModel.toggleTaskCompletion(task)
                                if (selectedFilter != "Completed" && !task.isCompleted) {
                                    viewModel.delayedRefresh()
                                }
                            }
                        )
                    }
                }
            }
            
            // Afficher le dialogue de filtres si visible
            if (uiState.isFilterDialogVisible) {
                FilterDialog(
                    selectedPriorities = uiState.selectedPriorities,
                    showCompletedTasks = uiState.showCompletedTasks,
                    sortOption = uiState.sortOption,
                    onTogglePriority = { viewModel.togglePriorityFilter(it) },
                    onToggleShowCompleted = { viewModel.toggleShowCompletedTasks() },
                    onSelectSortOption = { viewModel.setSortOption(it) },
                    onDismiss = { viewModel.toggleFilterDialog() }
                )
            }
            
            uiState.error?.let { error ->
                ErrorSnackbar(
                    message = error,
                    onDismiss = viewModel::clearError,
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TaskItem(
    task: Task,
    onClick: () -> Unit,
    onCheckedChange: () -> Unit,
    modifier: Modifier = Modifier
) {
    val cardColor = if (task.isCompleted) 
        Background.copy(alpha = 0.5f)
    else 
        Background
    
    val priorityColor = when (task.priority) {
        Priority.HIGH -> AccentColor
        Priority.MEDIUM -> SecondaryColor
        Priority.LOW -> PrimaryColor
    }
    
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(),
        colors = CardDefaults.cardColors(
            containerColor = cardColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (task.isCompleted) 0.dp else 2.dp,
            hoveredElevation = 4.dp
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Priority indicator with animation
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(if (task.isCompleted) Color.Gray else priorityColor)
                    .border(
                        width = 2.dp,
                        color = if (task.isCompleted) Color.Gray.copy(alpha = 0.3f) else priorityColor.copy(alpha = 0.3f),
                        shape = CircleShape
                    )
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = if (task.isCompleted) Color.Gray else TextColor,
                        textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                if (task.description.isNotBlank()) {
                    Text(
                        text = task.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (task.isCompleted) 
                            Color.Gray 
                        else 
                            TextColor,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                }
                
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Icône d'horloge avec date
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                if (task.isCompleted) 
                                    Color.Gray.copy(alpha = 0.1f)
                                else 
                                    PrimaryColor.copy(alpha = 0.1f)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Schedule,
                            contentDescription = null,
                            tint = if (task.isCompleted) Color.Gray else PrimaryColor,
                            modifier = Modifier.size(14.dp)
                        )
                        
                        Spacer(modifier = Modifier.width(4.dp))
                        
                        Text(
                            text = task.dueDateTime.format(
                                DateTimeFormatter.ofPattern("MMM dd, HH:mm")
                            ),
                            style = MaterialTheme.typography.bodySmall,
                            color = if (task.isCompleted) Color.Gray else PrimaryColor
                        )
                    }
                    
                    // Afficher l'icône de localisation si disponible
                    if (task.latitude != null) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(
                                    if (task.isCompleted) 
                                        Color.Gray.copy(alpha = 0.1f)
                                    else 
                                        SecondaryColor.copy(alpha = 0.1f)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.LocationOn,
                                contentDescription = null,
                                tint = if (task.isCompleted) Color.Gray else SecondaryColor,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }
                    
                    // Afficher le badge du projet si disponible
                    task.projectId?.let {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(
                                    if (task.isCompleted) 
                                        Color.Gray.copy(alpha = 0.1f)
                                    else 
                                        AccentColor.copy(alpha = 0.1f)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Folder,
                                contentDescription = null,
                                tint = if (task.isCompleted) Color.Gray else AccentColor,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }
                }
            }
            
            Checkbox(
                checked = task.isCompleted,
                onCheckedChange = { onCheckedChange() },
                colors = CheckboxDefaults.colors(
                    checkedColor = if (task.isCompleted) Color.Gray else PrimaryColor,
                    uncheckedColor = TextColor,
                    checkmarkColor = Background
                ),
                modifier = Modifier
                    .size(24.dp)
                    .padding(start = 8.dp)
            )
        }
    }
}

@Composable
private fun EmptyTasksMessage(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(32.dp)
            .fillMaxWidth(0.8f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(PrimaryColor.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Assignment,
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                tint = PrimaryColor
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = "No tasks yet",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                color = TextColor
            )
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "Create your first task by tapping the + button",
            style = MaterialTheme.typography.bodyLarge,
            color = TextColor,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun ErrorSnackbar(
    message: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Snackbar(
        modifier = modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(8.dp)),
        action = {
            TextButton(
                onClick = onDismiss,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = AccentColor
                )
            ) {
                Text("DISMISS")
            }
        },
        containerColor = AccentColor.copy(alpha = 0.1f),
        contentColor = AccentColor
    ) {
        Text(message)
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onCloseSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier.fillMaxWidth(),
        placeholder = { Text("Search tasks...") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = PrimaryColor
            )
        },
        trailingIcon = {
            IconButton(onClick = onCloseSearch) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close Search"
                )
            }
        },
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            unfocusedContainerColor = Background,
            focusedContainerColor = Background
        ),
        shape = RoundedCornerShape(16.dp)
    )
}

@Composable
fun FilterDialog(
    selectedPriorities: Set<Priority>,
    showCompletedTasks: Boolean,
    sortOption: SortOption,
    onTogglePriority: (Priority) -> Unit,
    onToggleShowCompleted: () -> Unit,
    onSelectSortOption: (SortOption) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Filter Tasks") },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Section priorités
                Text(
                    text = "Priority",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = TextColor
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val priorities = listOf(
                        Priority.HIGH to "High",
                        Priority.MEDIUM to "Medium",
                        Priority.LOW to "Low"
                    )
                    
                    priorities.forEach { (priority, label) ->
                        OutlinedCard(
                            modifier = Modifier.weight(1f),
                            colors = CardDefaults.outlinedCardColors(
                                containerColor = if (selectedPriorities.contains(priority)) 
                                    PrimaryColor.copy(alpha = 0.1f) 
                                else 
                                    Background
                            ),
                            border = BorderStroke(
                                width = 1.dp,
                                color = if (selectedPriorities.contains(priority))
                                    PrimaryColor
                                else
                                    Background
                            ),
                            onClick = { onTogglePriority(priority) }
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(label)
                            }
                        }
                    }
                }
                
                // Option pour afficher les tâches complétées
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Show completed tasks",
                        modifier = Modifier.weight(1f),
                        color = TextColor
                    )
                    Switch(
                        checked = showCompletedTasks,
                        onCheckedChange = { onToggleShowCompleted() }
                    )
                }
                
                // Options de tri
                Text(
                    text = "Sort by",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = TextColor
                )
                
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val sortOptions = listOf(
                        SortOption.DATE_ASC to "Date (Oldest first)",
                        SortOption.DATE_DESC to "Date (Newest first)",
                        SortOption.PRIORITY_HIGH to "Priority (High to Low)",
                        SortOption.PRIORITY_LOW to "Priority (Low to High)",
                        SortOption.TITLE_ASC to "Title (A to Z)",
                        SortOption.TITLE_DESC to "Title (Z to A)"
                    )
                    
                    sortOptions.forEach { (option, label) ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onSelectSortOption(option) }
                                .padding(vertical = 12.dp, horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = sortOption == option,
                                onClick = { onSelectSortOption(option) }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = label)
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss
            ) {
                Text("Apply")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text("Close")
            }
        }
    )
} 