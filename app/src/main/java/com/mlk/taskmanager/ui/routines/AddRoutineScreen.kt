package com.mlk.taskmanager.ui.routines

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import com.mlk.taskmanager.ui.theme.Background
import com.mlk.taskmanager.ui.theme.TextColor
import com.mlk.taskmanager.ui.theme.PrimaryColor
import com.mlk.taskmanager.ui.theme.SecondaryColor
import com.mlk.taskmanager.ui.theme.AccentColor
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
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockSelection
import com.mlk.taskmanager.ui.settings.SettingsViewModel
import java.time.DayOfWeek
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRoutineScreen(
    navController: NavController,
    viewModel: RoutinesViewModel = hiltViewModel(),
    settingsViewModel: SettingsViewModel = hiltViewModel()
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var time by remember { mutableStateOf(LocalTime.of(8, 0)) }
    var selectedDays by remember { mutableStateOf(setOf<DayOfWeek>()) }
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var showCategoryDialog by remember { mutableStateOf(false) }
    
    // Récupérer les catégories depuis les paramètres
    val settingsState by settingsViewModel.uiState.collectAsState()
    val categories = settingsState.categories
    
    val timePickerState = rememberUseCaseState()
    
    ClockDialog(
        state = timePickerState,
        selection = ClockSelection.HoursMinutes { hours, minutes ->
            time = LocalTime.of(hours, minutes)
        }
    )
    
    if (showCategoryDialog) {
        AlertDialog(
            onDismissRequest = { showCategoryDialog = false },
            title = { Text("Select Category", color = TextColor) },
            text = {
                Column {
                    Spacer(modifier = Modifier.height(8.dp))
                    if (categories.isEmpty()) {
                        Text(
                            "No categories available. Add categories in Settings.",
                            modifier = Modifier.padding(vertical = 12.dp),
                            color = TextColor
                        )
                    } else {
                        categories.forEach { category ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        selectedCategory = category
                                        showCategoryDialog = false
                                    }
                                    .padding(vertical = 12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = selectedCategory == category,
                                    onClick = {
                                        selectedCategory = category
                                        showCategoryDialog = false
                                    }
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(category, color = TextColor)
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showCategoryDialog = false }) {
                    Text("Close", color = PrimaryColor)
                }
            },
            containerColor = Color(0xFF121212)
        )
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Add Routine",
                        color = TextColor
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = TextColor)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Background,
                    titleContentColor = TextColor
                )
            )
        },
        containerColor = Background,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (title.isNotBlank() && selectedDays.isNotEmpty()) {
                        viewModel.addRoutine(
                            title = title,
                            description = description,
                            time = time,
                            repeatDays = selectedDays.toList(),
                            category = selectedCategory
                        )
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.padding(16.dp),
                containerColor = PrimaryColor,
                contentColor = TextColor
            ) {
                Icon(Icons.Default.Check, contentDescription = "Save Routine")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            
            // Title
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title", color = SecondaryColor) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = TextColor,
                    unfocusedTextColor = TextColor,
                    focusedBorderColor = PrimaryColor,
                    unfocusedBorderColor = SecondaryColor.copy(alpha = 0.5f),
                    cursorColor = PrimaryColor
                )
            )
            
            // Description
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description", color = SecondaryColor) },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = TextColor,
                    unfocusedTextColor = TextColor,
                    focusedBorderColor = PrimaryColor,
                    unfocusedBorderColor = SecondaryColor.copy(alpha = 0.5f),
                    cursorColor = PrimaryColor
                )
            )
            
            // Time
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1A1A)),
                border = BorderStroke(1.dp, PrimaryColor.copy(alpha = 0.3f))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { timePickerState.show() }
                        // Ajouter un fond plus clair au survol pour indiquer qu'il s'agit d'un élément cliquable
                        .background(Color(0xFF1A1A1A))
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Schedule,
                        contentDescription = null,
                        tint = PrimaryColor
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "Time",
                            style = MaterialTheme.typography.bodyMedium,
                            color = SecondaryColor
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = time.format(DateTimeFormatter.ofPattern("h:mm")),
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryColor
                                )
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = time.format(DateTimeFormatter.ofPattern("a")),
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = TextColor
                                )
                            )
                        }
                    }
                }
            }
            
            // Repeat Days
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF121212)),
                border = null
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Repeat Days",
                        style = MaterialTheme.typography.bodyMedium,
                        color = SecondaryColor
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        DayOfWeek.values().forEach { day ->
                            val isSelected = day in selectedDays
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (isSelected) PrimaryColor else Color(0xFF333333)
                                    )
                                    .clickable {
                                        selectedDays = if (isSelected) {
                                            selectedDays - day
                                        } else {
                                            selectedDays + day
                                        }
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = day.getDisplayName(TextStyle.NARROW, Locale.getDefault()),
                                    color = if (isSelected) TextColor else SecondaryColor
                                )
                            }
                        }
                    }
                }
            }
            
            // Category
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF121212)),
                border = null
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showCategoryDialog = true }
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Category,
                        contentDescription = null,
                        tint = PrimaryColor
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Category",
                            style = MaterialTheme.typography.bodyMedium,
                            color = SecondaryColor
                        )
                        Text(
                            text = selectedCategory ?: "Select a category",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = TextColor
                            )
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = null,
                        tint = SecondaryColor
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
} 