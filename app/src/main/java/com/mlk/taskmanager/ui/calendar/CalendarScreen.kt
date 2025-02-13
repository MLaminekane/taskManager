package com.mlk.taskmanager.ui.calendar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.TextStyle
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(
    navController: NavController,
    viewModel: CalendarViewModel = hiltViewModel()
) {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var selectedFilter by remember { mutableStateOf(TaskFilter.ALL) }
    var selectedTimeFilter by remember { mutableStateOf(TimeFilter.DAY) }
    
    val calendarState = rememberUseCaseState()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(selectedDate, selectedTimeFilter) {
        val startDateTime = when (selectedTimeFilter) {
            TimeFilter.DAY -> LocalDateTime.of(selectedDate, LocalTime.MIN)
            TimeFilter.WEEK -> LocalDateTime.of(selectedDate.minusDays(selectedDate.dayOfWeek.value.toLong() - 1), LocalTime.MIN)
            TimeFilter.MONTH -> LocalDateTime.of(selectedDate.withDayOfMonth(1), LocalTime.MIN)
        }
        
        val endDateTime = when (selectedTimeFilter) {
            TimeFilter.DAY -> LocalDateTime.of(selectedDate, LocalTime.MAX)
            TimeFilter.WEEK -> LocalDateTime.of(selectedDate.plusDays(7 - selectedDate.dayOfWeek.value.toLong()), LocalTime.MAX)
            TimeFilter.MONTH -> LocalDateTime.of(selectedDate.withDayOfMonth(selectedDate.lengthOfMonth()), LocalTime.MAX)
        }
        
        viewModel.loadTasks(startDateTime, endDateTime)
    }

    CalendarDialog(
        state = calendarState,
        selection = CalendarSelection.Date { date ->
            selectedDate = date
        },
        config = CalendarConfig(
            monthSelection = true,
            yearSelection = true
        )
    )

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier.background(Color.White)
            ) {
                TopAppBar(
                    title = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = when (selectedTimeFilter) {
                                    TimeFilter.DAY -> "Daily Tasks"
                                    TimeFilter.WEEK -> "Weekly Tasks"
                                    TimeFilter.MONTH -> "Monthly Tasks"
                                },
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                IconButton(
                                    onClick = { calendarState.show() }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.DateRange,
                                        contentDescription = "Show Calendar"
                                    )
                                }
                                IconButton(onClick = { /* Show notifications */ }) {
                                    Icon(
                                        imageVector = Icons.Default.Notifications,
                                        contentDescription = "Notifications"
                                    )
                                }
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White,
                        titleContentColor = Color.Black
                    )
                )

                // Time filter chips
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(TimeFilter.values()) { filter ->
                        FilterChip(
                            selected = selectedTimeFilter == filter,
                            onClick = { selectedTimeFilter = filter },
                            label = { 
                                Text(
                                    text = filter.label,
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontWeight = FontWeight.Medium
                                    )
                                ) 
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                containerColor = Color.Transparent,
                                selectedContainerColor = Color.White,
                                labelColor = Color.Gray,
                                selectedLabelColor = Color(0xFF613BE7)
                            ),
                            border = FilterChipDefaults.filterChipBorder(
                                borderColor = Color.LightGray,
                                selectedBorderColor = Color(0xFF613BE7),
                                borderWidth = 1.dp,
                                enabled = true,
                                selected = selectedTimeFilter == filter
                            ),
                            modifier = Modifier
                                .clip(RoundedCornerShape(100.dp))
                                .background(
                                    if (selectedTimeFilter == filter) 
                                        Color(0xFF613BE7).copy(alpha = 0.1f) 
                                    else 
                                        Color.White
                                )
                        )
                    }
                }
            }
        },
        containerColor = Color.White
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Date selector
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                val dates = when (selectedTimeFilter) {
                    TimeFilter.DAY -> (-2..2).map { selectedDate.plusDays(it.toLong()) }
                    TimeFilter.WEEK -> (-2..2).map { selectedDate.plusWeeks(it.toLong()) }
                    TimeFilter.MONTH -> (-2..2).map { selectedDate.plusMonths(it.toLong()) }
                }
                
                items(dates) { date ->
                    val isSelected = date == selectedDate
                    DateItem(
                        date = date,
                        isSelected = isSelected,
                        timeFilter = selectedTimeFilter,
                        onClick = { selectedDate = date }
                    )
                }
            }

            // Task Status Filter
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(TaskFilter.values()) { filter ->
                    Surface(
                        modifier = Modifier.clip(RoundedCornerShape(24.dp)),
                        color = if (selectedFilter == filter) 
                            filter.color.copy(alpha = 0.15f)
                        else 
                            Color.White,
                        border = BorderStroke(
                            width = 1.dp,
                            color = if (selectedFilter == filter) filter.color else Color.LightGray
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .clickable { selectedFilter = filter }
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = filter.icon,
                                contentDescription = null,
                                tint = if (selectedFilter == filter) filter.color else Color.Gray,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = filter.label,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = if (selectedFilter == filter) filter.color else Color.Gray,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tasks list
            if (uiState.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color(0xFF613BE7))
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    val filteredTasks = when (selectedFilter) {
                        TaskFilter.ALL -> uiState.tasks
                        TaskFilter.TODO -> uiState.tasks.filter { !it.isCompleted }
                        TaskFilter.IN_PROGRESS -> uiState.tasks.filter { !it.isCompleted }
                        TaskFilter.COMPLETED -> uiState.tasks.filter { it.isCompleted }
                    }
                    
                    if (filteredTasks.isEmpty()) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(32.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "No tasks found",
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        color = Color.Gray
                                    )
                                )
                            }
                        }
                    } else {
                        items(filteredTasks) { task ->
                            TaskItem(
                                title = task.title,
                                category = "Task",
                                time = task.dueDateTime.format(
                                    java.time.format.DateTimeFormatter.ofPattern("hh:mm a")
                                ),
                                status = when {
                                    task.isCompleted -> TaskStatus.DONE
                                    else -> TaskStatus.TODO
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

private enum class TimeFilter(val label: String) {
    DAY("Day"),
    WEEK("Week"),
    MONTH("Month")
}

private enum class TaskFilter(
    val label: String,
    val color: Color,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    ALL("All tasks", Color(0xFF613BE7), Icons.Default.FormatListBulleted),
    TODO("To do", Color(0xFF4B7BE5), Icons.Default.CheckBoxOutlineBlank),
    IN_PROGRESS("In Progress", Color(0xFFFF4C60), Icons.Default.Pending),
    COMPLETED("Completed", Color(0xFF4CAF50), Icons.Default.CheckBox)
}

@Composable
private fun DateItem(
    date: LocalDate,
    isSelected: Boolean,
    timeFilter: TimeFilter,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) Color(0xFF613BE7) else Color.White)
            .border(
                width = 1.dp,
                color = if (isSelected) Color(0xFF613BE7) else Color.LightGray,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(vertical = 12.dp, horizontal = 24.dp)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = when (timeFilter) {
                TimeFilter.DAY -> date.month.getDisplayName(TextStyle.SHORT, Locale.getDefault())
                TimeFilter.WEEK -> "Week"
                TimeFilter.MONTH -> date.year.toString()
            },
            style = MaterialTheme.typography.bodyMedium.copy(
                color = if (isSelected) Color.White else Color.Gray
            )
        )
        Text(
            text = when (timeFilter) {
                TimeFilter.DAY -> date.dayOfMonth.toString()
                TimeFilter.WEEK -> "${date.dayOfMonth}-${date.plusDays(6).dayOfMonth}"
                TimeFilter.MONTH -> date.month.getDisplayName(TextStyle.SHORT, Locale.getDefault())
            },
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = if (isSelected) Color.White else Color.Black
            )
        )
        Text(
            text = when (timeFilter) {
                TimeFilter.DAY -> date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
                TimeFilter.WEEK -> "${date.month.getDisplayName(TextStyle.SHORT, Locale.getDefault())}"
                TimeFilter.MONTH -> date.year.toString()
            },
            style = MaterialTheme.typography.bodyMedium.copy(
                color = if (isSelected) Color.White else Color.Gray
            )
        )
    }
}

@Composable
private fun TaskItem(
    title: String,
    category: String,
    time: String,
    status: TaskStatus
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = category,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray
                )
            )
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Schedule,
                        contentDescription = null,
                        tint = Color(0xFF613BE7),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = time,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color(0xFF613BE7)
                        )
                    )
                }
                StatusChip(status = status)
            }
        }
    }
}

@Composable
private fun StatusChip(status: TaskStatus) {
    val (backgroundColor, textColor, text) = when (status) {
        TaskStatus.TODO -> Triple(Color(0xFFE3EDFF), Color(0xFF4B7BE5), "To-do")
        TaskStatus.IN_PROGRESS -> Triple(Color(0xFFFFE9EA), Color(0xFFFF4C60), "In Progress")
        TaskStatus.DONE -> Triple(Color(0xFFE5FFE6), Color(0xFF4CAF50), "Done")
    }

    Surface(
        color = backgroundColor,
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            style = MaterialTheme.typography.bodySmall.copy(
                color = textColor,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

private enum class TaskStatus {
    TODO, IN_PROGRESS, DONE
} 