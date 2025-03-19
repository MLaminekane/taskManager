package com.mlk.taskmanager.ui.tasks

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockSelection
import com.mlk.taskmanager.data.model.Priority
import com.mlk.taskmanager.data.model.Project
import com.mlk.taskmanager.ui.settings.SettingsViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import androidx.compose.foundation.BorderStroke
import com.mlk.taskmanager.ui.home.HomeViewModel
import com.mlk.taskmanager.util.PlacesUtil
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    navController: NavController,
    viewModel: TasksViewModel = hiltViewModel(),
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    var title by remember { mutableStateOf("Task title") }
    var description by remember { mutableStateOf("Description....") }
    var projectName by remember { mutableStateOf("name...") }
    var selectedDate by remember { mutableStateOf(LocalDate.of(2025, 3, 15)) }
    var showDatePicker by remember { mutableStateOf(false) }
    var progress by remember { mutableStateOf(33) }
    var total by remember { mutableStateOf(45) }
    var priority by remember { mutableStateOf(Priority.MEDIUM) }
    var selectedTime by remember { mutableStateOf(LocalTime.now()) }
    var useLocation by remember { mutableStateOf(false) }
    var selectedLocation by remember { mutableStateOf<LatLng?>(null) }
    var locationRadius by remember { mutableStateOf(100f) }
    var showGroupDialog by remember { mutableStateOf(false) }
    var selectedGroup by remember { mutableStateOf("Work") }
    var isStarred by remember { mutableStateOf(false) }
    var showAddUserDialog by remember { mutableStateOf(false) }
    var newUserName by remember { mutableStateOf("") }
    var assignedUsers by remember { mutableStateOf(listOf("Lamine Kane")) }
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var showCategoryDialog by remember { mutableStateOf(false) }
    var showProjectDialog by remember { mutableStateOf(false) }
    var selectedProject by remember { mutableStateOf<Project?>(null) }

    val calendarState = rememberUseCaseState()
    val clockState = rememberUseCaseState()
    val scope = rememberCoroutineScope()

    val context = LocalContext.current
    val fusedLocationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            useLocation = true
        }
    }

    val settingsState by settingsViewModel.uiState.collectAsState()
    val categories = settingsState.categories

    val homeState by homeViewModel.uiState.collectAsState()

    var searchQuery by remember { mutableStateOf("") }
    var isSearching by remember { mutableStateOf(false) }
    var searchResults by remember { mutableStateOf<List<PlacesUtil.PlaceSearchResult>>(emptyList()) }
    var searchJob by remember { mutableStateOf<Job?>(null) }

    if (showGroupDialog) {
        AlertDialog(
            onDismissRequest = { showGroupDialog = false },
            title = { Text("Select Task Group") },
            text = {
                Column {
                    listOf("Work", "Personal", "Shopping", "Health", "Education").forEach { group ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    selectedGroup = group
                                    showGroupDialog = false
                                }
                                .padding(vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = selectedGroup == group,
                                onClick = {
                                    selectedGroup = group
                                    showGroupDialog = false
                                }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(group)
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showGroupDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    if (showAddUserDialog) {
        AlertDialog(
            onDismissRequest = { 
                showAddUserDialog = false
                newUserName = ""
            },
            title = { Text("Add User") },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    OutlinedTextField(
                        value = newUserName,
                        onValueChange = { newUserName = it },
                        label = { Text("User Name") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF613BE7),
                            focusedLabelColor = Color(0xFF613BE7)
                        )
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (newUserName.isNotBlank()) {
                            assignedUsers = assignedUsers + newUserName
                            showAddUserDialog = false
                            newUserName = ""
                        }
                    }
                ) {
                    Text("Add", color = Color(0xFF613BE7))
                }
            },
            dismissButton = {
                TextButton(onClick = { 
                    showAddUserDialog = false
                    newUserName = ""
                }) {
                    Text("Cancel")
                }
            }
        )
    }

    if (showProjectDialog) {
        AlertDialog(
            onDismissRequest = { showProjectDialog = false },
            title = { Text("Select Project") },
            text = {
                LazyColumn {
                    items(homeState.projects) { project ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    selectedProject = project
                                    showProjectDialog = false
                                }
                                .padding(vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(Color(project.color))
                                    .padding(6.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = project.name,
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        fontWeight = FontWeight.Medium
                                    )
                                )
                                Text(
                                    text = project.description,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = { showProjectDialog = false }
                ) {
                    Text("Close")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IconButton(onClick = { navController.navigateUp() }) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                            }
                            Text(
                                text = "Create new task",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }
                        
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                )
            )
        },
        containerColor = Color.White
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Title with Progress
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Enter task title") },
                    textStyle = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.LightGray,
                        focusedBorderColor = Color(0xFF613BE7)
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "$progress/$total",
                    color = Color(0xFF613BE7),
                    style = MaterialTheme.typography.titleMedium
                )
            }

            // Task Description
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Task description",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Medium
                    )
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.LightGray,
                        focusedBorderColor = Color(0xFF613BE7)
                    )
                )
            }

            // Project field
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Folder,
                        contentDescription = "Project",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Project",
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.Gray
                        )
                        Text(
                            text = selectedProject?.name ?: "Select a project",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    IconButton(onClick = { showProjectDialog = true }) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Select Project"
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))


            // Assigned To
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Assigned to",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Medium
                    )
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    assignedUsers.forEach { userName ->
                        Surface(
                            modifier = Modifier
                                .height(40.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .border(1.dp, Color.LightGray, RoundedCornerShape(20.dp)),
                            color = Color.White
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Surface(
                                    modifier = Modifier.size(24.dp),
                                    shape = CircleShape,
                                    color = Color(0xFF613BE7).copy(alpha = 0.1f)
                                ) {
                                    Text(
                                        text = userName.take(1).uppercase(),
                                        color = Color(0xFF613BE7),
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .wrapContentSize(Alignment.Center),
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            fontWeight = FontWeight.Medium
                                        )
                                    )
                                }
                                Text(userName)
                                IconButton(
                                    onClick = { 
                                        assignedUsers = assignedUsers.filter { it != userName }
                                    },
                                    modifier = Modifier.size(16.dp)
                                ) {
                                    Icon(
                                        Icons.Default.Close,
                                        contentDescription = "Remove",
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }
                    }
                    IconButton(
                        onClick = { showAddUserDialog = true },
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFF5F5F5))
                    ) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Add user",
                            tint = Color.Gray
                        )
                    }
                }
            }

            // Deadline
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Deadline",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Medium
                    )
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Date Field
                    OutlinedTextField(
                        value = selectedDate.format(DateTimeFormatter.ofPattern("MMM dd, yyyy")),
                        onValueChange = { },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(16.dp),
                        readOnly = true,
                        trailingIcon = {
                            IconButton(onClick = { calendarState.show() }) {
                                Icon(
                                    Icons.Default.CalendarToday,
                                    contentDescription = "Select date",
                                    tint = Color.Gray
                                )
                            }
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color.LightGray,
                            focusedBorderColor = Color(0xFF613BE7)
                        )
                    )
                    
                    // Time Field
                    OutlinedTextField(
                        value = selectedTime.format(DateTimeFormatter.ofPattern("HH:mm")),
                        onValueChange = { },
                        modifier = Modifier.width(120.dp),
                        shape = RoundedCornerShape(16.dp),
                        readOnly = true,
                        trailingIcon = {
                            IconButton(onClick = { clockState.show() }) {
                                Icon(
                                    Icons.Default.Schedule,
                                    contentDescription = "Select time",
                                    tint = Color.Gray
                                )
                            }
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color.LightGray,
                            focusedBorderColor = Color(0xFF613BE7)
                        )
                    )
                }
            }

            // Priority Selection
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Priority",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Priority.values().forEach { priorityOption ->
                            FilterChip(
                                selected = priority == priorityOption,
                                onClick = { priority = priorityOption },
                                label = { Text(priorityOption.name) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = when(priorityOption) {
                                        Priority.HIGH -> Color.Red.copy(alpha = 0.1f)
                                        Priority.MEDIUM -> Color(0xFF613BE7).copy(alpha = 0.1f)
                                        Priority.LOW -> Color.Green.copy(alpha = 0.1f)
                                    },
                                    selectedLabelColor = when(priorityOption) {
                                        Priority.HIGH -> Color.Red
                                        Priority.MEDIUM -> Color(0xFF613BE7)
                                        Priority.LOW -> Color.Green
                                    }
                                ),
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }

            // Category Selection
            Text(
                text = "Category",
                style = MaterialTheme.typography.titleMedium
            )
            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showCategoryDialog = true },
                border = BorderStroke(
                    width = 1.dp,
                    color = if (selectedCategory != null) 
                        MaterialTheme.colorScheme.primary 
                    else 
                        MaterialTheme.colorScheme.outline
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = selectedCategory ?: "Select Category",
                        color = if (selectedCategory != null) 
                            MaterialTheme.colorScheme.onSurface 
                        else 
                            MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Select Category"
                    )
                }
            }

            // Location Selection
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = null,
                                tint = Color(0xFF613BE7)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Location Reminder",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.SemiBold
                                )
                            )
                        }
                        Switch(
                            checked = useLocation,
                            onCheckedChange = { checked ->
                                if (checked) {
                                    when (PackageManager.PERMISSION_GRANTED) {
                                        ContextCompat.checkSelfPermission(
                                            context,
                                            Manifest.permission.ACCESS_FINE_LOCATION
                                        ) -> useLocation = true
                                        else -> locationPermissionLauncher.launch(
                                            Manifest.permission.ACCESS_FINE_LOCATION
                                        )
                                    }
                                } else {
                                    useLocation = false
                                    selectedLocation = null
                                }
                            },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color(0xFF613BE7),
                                checkedTrackColor = Color(0xFF613BE7).copy(alpha = 0.5f)
                            )
                        )
                    }

                    if (useLocation) {
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        // Location search bar
                        OutlinedTextField(
                            value = searchQuery,
                            onValueChange = { query ->
                                searchQuery = query
                                // Annuler la recherche précédente si elle est en cours
                                searchJob?.cancel()
                                
                                if (query.length >= 3) {
                                    isSearching = true
                                    // Délai pour éviter de faire trop de requêtes pendant la frappe
                                    searchJob = scope.launch {
                                        delay(500) // Délai de 500ms
                                        try {
                                            // Rechercher avec l'API Places
                                            val results = PlacesUtil.searchPlaces(query)
                                            searchResults = results
                                        } catch (e: Exception) {
                                            // En cas d'erreur, afficher un message de débogage
                                            println("Erreur de recherche Places: ${e.message}")
                                            // Fallback sur quelques résultats simulés
                                            searchResults = listOf(
                                                PlacesUtil.PlaceSearchResult(
                                                    placeId = "error_fallback_1",
                                                    mainText = "Erreur de connexion",
                                                    secondaryText = "Vérifiez votre connexion internet",
                                                    fullText = "Erreur de connexion - Vérifiez votre connexion internet"
                                                )
                                            )
                                        } finally {
                                            isSearching = false
                                        }
                                    }
                                } else {
                                    searchResults = emptyList()
                                    isSearching = false
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp)),
                            placeholder = { Text("Rechercher une adresse...") },
                            leadingIcon = { 
                                Icon(
                                    imageVector = Icons.Default.Search, 
                                    contentDescription = "Search",
                                    tint = Color(0xFF613BE7)
                                )
                            },
                            trailingIcon = {
                                if (searchQuery.isNotEmpty()) {
                                    IconButton(onClick = { 
                                        searchQuery = "" 
                                        searchResults = emptyList()
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.Clear, 
                                            contentDescription = "Clear"
                                        )
                                    }
                                }
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = Color.LightGray,
                                focusedBorderColor = Color(0xFF613BE7),
                                cursorColor = Color(0xFF613BE7)
                            ),
                            singleLine = true
                        )
                        
                        // Search results
                        if (searchResults.isNotEmpty()) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(max = 200.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                ),
                                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                            ) {
                                LazyColumn {
                                    items(searchResults) { result ->
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clickable {
                                                    // Récupérer les détails du lieu sélectionné
                                                    scope.launch {
                                                        try {
                                                            if (result.placeId.startsWith("error_fallback")) {
                                                                // Cas de fallback
                                                                selectedLocation = LatLng(48.427362, -71.067948) // Chicoutimi par défaut
                                                            } else {
                                                                // Appel à l'API pour obtenir les coordonnées
                                                                val placeDetails = PlacesUtil.getPlaceDetails(result.placeId)
                                                                selectedLocation = placeDetails.latLng
                                                            }
                                                            searchQuery = result.fullText
                                                            searchResults = emptyList()
                                                        } catch (e: Exception) {
                                                            println("Erreur lors de la récupération des détails du lieu: ${e.message}")
                                                        }
                                                    }
                                                }
                                                .padding(16.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.LocationOn,
                                                contentDescription = null,
                                                tint = Color.Gray,
                                                modifier = Modifier.size(20.dp)
                                            )
                                            Spacer(modifier = Modifier.width(12.dp))
                                            Column {
                                                Text(
                                                    text = result.mainText,
                                                    style = MaterialTheme.typography.bodyMedium.copy(
                                                        fontWeight = FontWeight.Medium
                                                    )
                                                )
                                                Text(
                                                    text = result.secondaryText,
                                                    style = MaterialTheme.typography.bodySmall,
                                                    color = Color.Gray
                                                )
                                            }
                                        }
                                        if (searchResults.last() != result) {
                                            Divider(
                                                modifier = Modifier.padding(horizontal = 16.dp),
                                                color = Color.LightGray.copy(alpha = 0.5f)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        
                        if (isSearching) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(24.dp),
                                    color = Color(0xFF613BE7),
                                    strokeWidth = 2.dp
                                )
                            }
                        }
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        // Map - Assurer que cameraPositionState est mis à jour
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .border(1.dp, Color.LightGray, RoundedCornerShape(16.dp))
                        ) {
                            val cameraPositionState = rememberCameraPositionState {
                                position = CameraPosition.fromLatLngZoom(
                                    selectedLocation ?: LatLng(37.7749, -122.4194), // Position par défaut (San Francisco)
                                    14f
                                )
                            }

                            // Mettre à jour la position de la caméra quand la localisation change
                            LaunchedEffect(selectedLocation) {
                                selectedLocation?.let {
                                    cameraPositionState.animate(
                                        update = CameraUpdateFactory.newCameraPosition(
                                            CameraPosition.fromLatLngZoom(it, 14f)
                                        ),
                                        durationMs = 1000
                                    )
                                }
                            }

                            GoogleMap(
                                modifier = Modifier.fillMaxSize(),
                                cameraPositionState = cameraPositionState,
                                onMapClick = { latLng ->
                                    selectedLocation = latLng
                                    searchQuery = "Position sélectionnée (${latLng.latitude.format(2)}, ${latLng.longitude.format(2)})"
                                },
                                properties = MapProperties(
                                    isMyLocationEnabled = true,
                                    mapType = MapType.NORMAL,
                                    // Tentative de forcer la carte à s'afficher même si l'API a des problèmes
                                    isBuildingEnabled = true,
                                    isIndoorEnabled = false,
                                    isTrafficEnabled = false,
                                    minZoomPreference = 5f,
                                    maxZoomPreference = 20f
                                ),
                                uiSettings = MapUiSettings(
                                    zoomControlsEnabled = true,
                                    myLocationButtonEnabled = true,
                                    mapToolbarEnabled = false,
                                    compassEnabled = true,
                                    rotationGesturesEnabled = true,
                                    tiltGesturesEnabled = true,
                                    zoomGesturesEnabled = true
                                )
                            ) {
                                selectedLocation?.let { location ->
                                    // Ajouter un cercle rouge comme marqueur secondaire pour plus de visibilité
                                    Circle(
                                        center = location,
                                        radius = 10.0,
                                        fillColor = Color.Red,
                                        strokeColor = Color.White,
                                        strokeWidth = 2f
                                    )
                                    
                                    // Marker principal
                                    Marker(
                                        state = MarkerState(position = location),
                                        title = "Emplacement de la tâche",
                                        snippet = "Vous recevrez une notification en approchant de cet emplacement"
                                    )
                                    
                                    // Ajouter un cercle pour montrer le rayon
                                    Circle(
                                        center = location,
                                        radius = locationRadius.toDouble(),
                                        fillColor = Color(0xFF613BE7).copy(alpha = 0.1f),
                                        strokeColor = Color(0xFF613BE7),
                                        strokeWidth = 2f
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        
                        // Radius selection with visual feedback
                        Column {
                            Text(
                                text = "Notification Radius",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.Medium
                                ),
                                color = Color.DarkGray
                            )
                            
                            Spacer(modifier = Modifier.height(4.dp))
                            
                            Text(
                                text = "You'll be notified when you enter within ${locationRadius.toInt()} meters of this location",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray
                            )
                            
                            Spacer(modifier = Modifier.height(8.dp))
                            
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "50m",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.Gray
                                )
                                
                                Slider(
                                    value = locationRadius,
                                    onValueChange = { locationRadius = it },
                                    valueRange = 50f..500f,
                                    steps = 9,
                                    colors = SliderDefaults.colors(
                                        thumbColor = Color(0xFF613BE7),
                                        activeTrackColor = Color(0xFF613BE7),
                                        inactiveTrackColor = Color.LightGray
                                    ),
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(horizontal = 8.dp)
                                )
                                
                                Text(
                                    text = "500m",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.Gray
                                )
                            }
                            
                            // Radius indicators
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                listOf(
                                    "Small" to 50f,
                                    "Medium" to 200f,
                                    "Large" to 500f
                                ).forEach { (label, value) ->
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(12.dp)
                                                .clip(CircleShape)
                                                .background(
                                                    if (locationRadius.toInt() == value.toInt()) 
                                                        Color(0xFF613BE7) 
                                                    else 
                                                        Color.LightGray
                                                )
                                                .clickable { locationRadius = value }
                                        )
                                        
                                        Spacer(modifier = Modifier.height(4.dp))
                                        
                                        Text(
                                            text = label,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = if (locationRadius.toInt() == value.toInt()) 
                                                Color(0xFF613BE7) 
                                            else 
                                                Color.Gray
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Add Task button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        // Create task
                        val dueDateTime = LocalDateTime.of(selectedDate, selectedTime)
                        viewModel.addTask(
                            title = title.takeIf { it != "Task title" } ?: "New Task",
                            description = description.takeIf { it != "Description...." } ?: "",
                            dueDateTime = dueDateTime,
                            priority = priority,
                            category = selectedCategory,
                            latitude = if (useLocation && selectedLocation != null) selectedLocation?.latitude else null,
                            longitude = if (useLocation && selectedLocation != null) selectedLocation?.longitude else null,
                            locationRadius = if (useLocation && selectedLocation != null) locationRadius else null,
                            projectId = selectedProject?.id
                        )
                        // Show success message or navigate back
                        navController.navigateUp()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF613BE7)
                    )
                ) {
                    Text(
                        text = "Create Task",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(80.dp))
        }
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

    ClockDialog(
        state = clockState,
        selection = ClockSelection.HoursMinutes { hours, minutes ->
            selectedTime = LocalTime.of(hours, minutes)
        }
    )

    // Category selection dialog
    if (showCategoryDialog) {
        AlertDialog(
            onDismissRequest = { showCategoryDialog = false },
            title = { Text("Select Category") },
            text = {
                LazyColumn {
                    items(categories) { category ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    selectedCategory = category
                                    showCategoryDialog = false
                                }
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(category)
                            if (category == selectedCategory) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Selected",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showCategoryDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

// Extension pour formater les doubles pour les coordonnées
private fun Double.format(digits: Int) = "%.${digits}f".format(this)