package com.mlk.taskmanager.ui.calendar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import com.mlk.taskmanager.ui.theme.Background
import com.mlk.taskmanager.ui.theme.TextColor
import com.mlk.taskmanager.ui.theme.PrimaryColor
import com.mlk.taskmanager.ui.theme.SecondaryColor
import com.mlk.taskmanager.ui.theme.AccentColor
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
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    // Use the app's theme colors
    val primaryColor = PrimaryColor // Orange
    val secondaryColor = SecondaryColor // Beige
    val backgroundColor = Background // Black
    val cardBackgroundColor = Color(0xFF121212) // Dark Gray for cards
    val textColor = TextColor // White
    val accentColor = AccentColor // Red
    val accentGradient = Brush.horizontalGradient(
        colors = listOf(
            primaryColor,
            accentColor
        )
    )

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
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shadowElevation = 4.dp,
                color = backgroundColor
            ) {
                Column {
                    TopAppBar(
                        title = {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        text = when (selectedTimeFilter) {
                                            TimeFilter.DAY -> "Daily View"
                                            TimeFilter.WEEK -> "Weekly Planner"
                                            TimeFilter.MONTH -> "Monthly Overview"
                                        },
                                        style = MaterialTheme.typography.titleLarge,
                                        color = textColor,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 22.sp,
                                        letterSpacing = (-0.5).sp
                                    )
                                    Text(
                                        text = selectedDate.format(java.time.format.DateTimeFormatter.ofPattern("MMMM yyyy")),
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            color = secondaryColor,
                                            fontSize = 14.sp
                                        )
                                    )
                                }
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    IconButton(
                                        onClick = { calendarState.show() },
                                        modifier = Modifier
                                            .size(40.dp)
                                            .clip(CircleShape)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Rounded.DateRange,
                                            contentDescription = "Show Calendar",
                                            tint = primaryColor
                                        )
                                    }
                                }
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = backgroundColor,
                            titleContentColor = textColor
                        )
                    )

                    // Time filter chips with improved design
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(TimeFilter.values()) { filter ->
                            Surface(
                                modifier = Modifier.clip(RoundedCornerShape(8.dp)),
                                color = if (selectedTimeFilter == filter)
                                    primaryColor
                                else
                                    cardBackgroundColor,
                                border = BorderStroke(
                                    width = 1.dp,
                                    color = if (selectedTimeFilter == filter)
                                        Color.Transparent
                                    else
                                        Color.DarkGray
                                ),shape = RoundedCornerShape(8.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .clickable { selectedTimeFilter = filter }
                                        .padding(horizontal = 16.dp, vertical = 10.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(
                                        imageVector = when (filter) {
                                            TimeFilter.DAY -> Icons.Rounded.Today
                                            TimeFilter.WEEK -> Icons.Rounded.ViewWeek
                                            TimeFilter.MONTH -> Icons.Rounded.CalendarMonth
                                        },
                                        contentDescription = null,
                                        tint = if (selectedTimeFilter == filter)
                                            textColor
                                        else
                                            secondaryColor,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Text(
                                        text = filter.label,
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            color = if (selectedTimeFilter == filter)
                                                textColor
                                            else
                                                secondaryColor,
                                            fontWeight = FontWeight.Medium
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        },
        containerColor = backgroundColor
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Enhanced date selector with animations
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = cardBackgroundColor),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    val dates = when (selectedTimeFilter) {
                        TimeFilter.DAY -> (-3..3).map { selectedDate.plusDays(it.toLong()) }
                        TimeFilter.WEEK -> (-2..2).map { selectedDate.plusWeeks(it.toLong()) }
                        TimeFilter.MONTH -> (-2..2).map { selectedDate.plusMonths(it.toLong()) }
                    }

                    items(dates) { date ->
                        val isSelected = date == selectedDate
                        DateItem(
                            date = date,
                            isSelected = isSelected,
                            timeFilter = selectedTimeFilter,
                            primaryColor = primaryColor,
                            onClick = { selectedDate = date }
                        )
                    }
                }
            }

            // Task Status Filter with improved visuals
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(TaskFilter.values()) { filter ->
                    Surface(
                        modifier = Modifier.padding(8.dp),
                        color = if (selectedFilter == filter) filter.color.copy(alpha = 0.15f) else cardBackgroundColor,
                        border = BorderStroke(
                            width = 1.dp,
                            color = if (selectedFilter == filter) filter.color else Color.DarkGray
                        ),shape = RoundedCornerShape(8.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .clickable { selectedFilter = filter }
                                .padding(horizontal = 16.dp, vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = filter.icon,
                                contentDescription = null,
                                tint = if (selectedFilter == filter) filter.color else secondaryColor,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = filter.label,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = if (selectedFilter == filter) filter.color else secondaryColor,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tasks list with improved card design
            if (uiState.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = primaryColor)
                }
            } else {
                AnimatedVisibility(
                    visible = true,
                    enter = fadeIn(animationSpec = tween(300)),
                    exit = fadeOut(animationSpec = tween(300))
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(bottom = 24.dp)
                    ) {
                        val filteredTasks = when (selectedFilter) {
                            TaskFilter.ALL -> uiState.tasks
                            TaskFilter.TODO -> uiState.tasks.filter { !it.isCompleted }
                            TaskFilter.IN_PROGRESS -> uiState.tasks.filter { !it.isCompleted }
                            TaskFilter.COMPLETED -> uiState.tasks.filter { it.isCompleted }
                        }

                        if (filteredTasks.isEmpty()) {
                            item {
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 24.dp),
                                    colors = CardDefaults.cardColors(containerColor = cardBackgroundColor),
                                    shape = RoundedCornerShape(16.dp)
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(32.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Rounded.CheckCircle,
                                            contentDescription = null,
                                            tint = secondaryColor,
                                            modifier = Modifier.size(48.dp)
                                        )
                                        Spacer(modifier = Modifier.height(16.dp))
                                        Text(
                                            text = "No tasks found",
                                            style = MaterialTheme.typography.bodyLarge.copy(
                                                color = secondaryColor,
                                                fontWeight = FontWeight.Medium
                                            )
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Text(
                                            text = "Enjoy your free time!",
                                            style = MaterialTheme.typography.bodyMedium.copy(
                                                color = secondaryColor.copy(alpha = 0.7f)
                                            )
                                        )
                                    }
                                }
                            }
                        } else {
                            items(filteredTasks) { task ->
                                EnhancedTaskItem(
                                    title = task.title,
                                    category = "Task",
                                    time = task.dueDateTime.format(
                                        java.time.format.DateTimeFormatter.ofPattern("hh:mm a")
                                    ),
                                    status = when {
                                        task.isCompleted -> TaskStatus.DONE
                                        else -> TaskStatus.TODO
                                    },
                                    primaryColor = primaryColor,
                                    onEditClick = { /* Edit task */ },
                                    onDeleteClick = { /* Delete task */ }
                                )
                            }
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
    ALL("All tasks", TextColor, Icons.Rounded.FormatListBulleted),
    TODO("To do", PrimaryColor, Icons.Rounded.CheckBoxOutlineBlank),
    IN_PROGRESS("In Progress", AccentColor, Icons.Rounded.Pending),
    COMPLETED("Completed", SecondaryColor, Icons.Rounded.CheckBox)
}

@Composable
private fun DateItem(
    date: LocalDate,
    isSelected: Boolean,
    timeFilter: TimeFilter,
    primaryColor: Color,
    onClick: () -> Unit
) {
    val cardBackgroundColor = Color(0xFF121212) // Dark Gray for cards
    val textColor = TextColor // White
    val secondaryColor = SecondaryColor // Beige
    val today = LocalDate.now()
    val isToday = date.equals(today)

    Surface(
        modifier = Modifier
            .width(90.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick),
        color = when {
            isSelected -> primaryColor
            isToday -> primaryColor.copy(alpha = 0.3f)
            else -> cardBackgroundColor
        },
        shadowElevation = if (isSelected) 4.dp else 0.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = when (timeFilter) {
                    TimeFilter.DAY -> date.month.getDisplayName(TextStyle.SHORT, Locale.getDefault())
                    TimeFilter.WEEK -> "Week"
                    TimeFilter.MONTH -> date.year.toString()
                },
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = if (isSelected) textColor.copy(alpha = 0.8f) else secondaryColor
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(
                        if (isToday && !isSelected)
                            primaryColor.copy(alpha = 0.3f)
                        else if (isSelected)
                            textColor.copy(alpha = 0.2f)
                        else
                            Color.Transparent
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = when (timeFilter) {
                        TimeFilter.DAY -> date.dayOfMonth.toString()
                        TimeFilter.WEEK -> "${date.dayOfMonth}"
                        TimeFilter.MONTH -> date.month.getDisplayName(TextStyle.SHORT, Locale.getDefault())
                    },
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) textColor else if (isToday) primaryColor else textColor
                    )
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = when (timeFilter) {
                    TimeFilter.DAY -> date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
                    TimeFilter.WEEK -> "${date.plusDays(6).dayOfMonth}"
                    TimeFilter.MONTH -> date.year.toString()
                },
                style = MaterialTheme.typography.bodySmall.copy(
                    color = if (isSelected) textColor.copy(alpha = 0.8f) else secondaryColor
                )
            )
        }
    }
}

@Composable
private fun EnhancedTaskItem(
    title: String,
    category: String,
    time: String,
    status: TaskStatus,
    primaryColor: Color,
    onEditClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {}
) {
    val cardBackgroundColor = Color(0xFF121212) // Dark Gray for cards
    val backgroundColor = Background // Black
    val secondaryColor = SecondaryColor // Beige
    val statusColors = mapOf(
        TaskStatus.TODO to PrimaryColor, // Orange primary color
        TaskStatus.IN_PROGRESS to AccentColor, // Red accent color
        TaskStatus.DONE to SecondaryColor // Beige secondary color
    )

    val statusColor = statusColors[status] ?: primaryColor

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Task detail */ },
        colors = CardDefaults.cardColors(containerColor = cardBackgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            // Status indicator
            Box(
                modifier = Modifier
                    .width(8.dp)
                    .fillMaxHeight()
                    .background(backgroundColor)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(statusColor.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = when(status) {
                                    TaskStatus.TODO -> Icons.Rounded.AssignmentLate
                                    TaskStatus.IN_PROGRESS -> Icons.Rounded.Assignment
                                    TaskStatus.DONE -> Icons.Rounded.AssignmentTurnedIn
                                },
                                contentDescription = null,
                                tint = statusColor,
                                modifier = Modifier.size(18.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column {
                            Text(
                                text = category,
                                style = MaterialTheme.typography.labelMedium.copy(
                                    color = secondaryColor,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                            Text(
                                text = title,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }

                    StatusChip(
                        status = status,
                        statusColor = statusColor
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Divider(color = Color.DarkGray.copy(alpha = 0.5f))

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Rounded.Schedule,
                            contentDescription = null,
                            tint = primaryColor,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = time,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = primaryColor,
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Rounded.Edit,
                            contentDescription = "Edit",
                            tint = secondaryColor,
                            modifier = Modifier
                                .size(20.dp)
                                .clip(CircleShape)
                                .clickable(onClick = onEditClick)
                                .padding(2.dp)
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Icon(
                            imageVector = Icons.Rounded.Delete,
                            contentDescription = "Delete",
                            tint = secondaryColor,
                            modifier = Modifier
                                .size(20.dp)
                                .clip(CircleShape)
                                .clickable(onClick = onDeleteClick)
                                .padding(2.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun StatusChip(status: TaskStatus, statusColor: Color) {
    val (backgroundColor, textColor, text) = when (status) {
        TaskStatus.TODO -> Triple(statusColor.copy(alpha = 0.1f), statusColor, "To-do")
        TaskStatus.IN_PROGRESS -> Triple(statusColor.copy(alpha = 0.1f), statusColor, "In Progress")
        TaskStatus.DONE -> Triple(statusColor.copy(alpha = 0.1f), statusColor, "Done")
    }

    Surface(
        color = backgroundColor,
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelSmall.copy(
                color = textColor,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.sp
            )
        )
    }
}

private enum class TaskStatus {
    TODO, IN_PROGRESS, DONE
}