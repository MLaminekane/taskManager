package com.mlk.taskmanager.ui.tasks

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
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

// Extension pour formater les doubles pour les coordonnées
fun Double.format(digits: Int) = "%.${digits}f".format(this)


@Composable
fun TaskTitleField(
    title: String,
    onTitleChange: (String) -> Unit,
    progress: Int,
    total: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = onTitleChange,
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
}

@Composable
fun TaskDescriptionField(
    description: String,
    onDescriptionChange: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "Task description",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Medium
            )
        )
        OutlinedTextField(
            value = description,
            onValueChange = onDescriptionChange,
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
}

@Composable
fun DateTimeSelector(
    selectedDate: LocalDate,
    selectedTime: LocalTime,
    onDateClick: () -> Unit,
    onTimeClick: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "Date and time",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Medium
            )
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Date Field
            OutlinedTextField(
                value = selectedDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")),
                onValueChange = { },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(16.dp),
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = onDateClick) {
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
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // Time Field
            OutlinedTextField(
                value = selectedTime.format(DateTimeFormatter.ofPattern("HH:mm")),
                onValueChange = { },
                modifier = Modifier.width(120.dp),
                shape = RoundedCornerShape(16.dp),
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = onTimeClick) {
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
}

@Composable
fun PrioritySelector(
    selectedPriority: Priority,
    onPrioritySelected: (Priority) -> Unit
) {
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
                        selected = selectedPriority == priorityOption,
                        onClick = { onPrioritySelected(priorityOption) },
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
}

@Composable
fun CategorySelector(
    selectedCategory: String?,
    onCategoryClick: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "Category",
            style = MaterialTheme.typography.titleMedium
        )
        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onCategoryClick),
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
    }
}

@Composable
fun ProjectSelector(
    selectedProject: Project?,
    onProjectClick: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "Project",
            style = MaterialTheme.typography.titleMedium
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onProjectClick),
            colors = CardDefaults.cardColors(
                containerColor = if (selectedProject != null) 
                    Color(0xFFF8F7FC) 
                else 
                    Color.White
            ),
            border = BorderStroke(
                width = 1.dp,
                color = if (selectedProject != null) 
                    Color(0xFF613BE7) 
                else 
                    Color.LightGray
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            if (selectedProject != null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(Color(selectedProject.color))
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = selectedProject.name,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                        Text(
                            text = selectedProject.description,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }
                }
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Assign to a project",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                    
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Select Project",
                        tint = Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
fun CreateTaskButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = onClick,
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
}

@Composable
fun LocationSearchField(
    query: String,
    onQueryChange: (String) -> Unit
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
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
            if (query.isNotEmpty()) {
                IconButton(onClick = { onQueryChange("") }) {
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
}

@Composable
fun LocationSearchResults(
    results: List<PlacesUtil.PlaceSearchResult>,
    onResultClick: (PlacesUtil.PlaceSearchResult) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 200.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        LazyColumn {
            items(results) { result ->
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onResultClick(result) }
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
                    
                    if (results.last() != result) {
                        HorizontalDivider(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            color = Color.LightGray.copy(alpha = 0.5f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LocationSearchSection(
    useLocation: Boolean,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    isSearching: Boolean,
    searchResults: List<PlacesUtil.PlaceSearchResult>,
    onResultClick: (PlacesUtil.PlaceSearchResult) -> Unit,
    onToggleLocation: (Boolean) -> Unit
) {
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
            // En-tête
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
                    onCheckedChange = onToggleLocation,
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color(0xFF613BE7),
                        checkedTrackColor = Color(0xFF613BE7).copy(alpha = 0.5f)
                    )
                )
            }

            if (useLocation) {
                Spacer(modifier = Modifier.height(16.dp))
                
                // Champ de recherche
                LocationSearchField(
                    query = searchQuery,
                    onQueryChange = onSearchQueryChange
                )
                
                // Affichage des résultats de recherche
                if (searchResults.isNotEmpty()) {
                    LocationSearchResults(
                        results = searchResults,
                        onResultClick = onResultClick
                    )
                }
                
                // Indicateur de chargement
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
            }
        }
    }
}

@Composable
fun MapView(
    selectedLocation: LatLng?,
    onMapClick: (LatLng) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, Color.LightGray, RoundedCornerShape(16.dp))
    ) {
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(
                selectedLocation ?: PlacesUtil.DEFAULT_LOCATION, 
                15f
            )
        }

        // Mise à jour de la position quand la localisation change
        LaunchedEffect(selectedLocation) {
            if (selectedLocation != null) {
                cameraPositionState.animate(
                    CameraUpdateFactory.newLatLngZoom(selectedLocation, 15f)
                )
            } else {
                cameraPositionState.animate(
                    CameraUpdateFactory.newLatLngZoom(PlacesUtil.DEFAULT_LOCATION, 13f)
                )
            }
        }

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapClick = onMapClick,
            properties = MapProperties(
                isMyLocationEnabled = true,
                mapType = MapType.NORMAL,
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
                compassEnabled = true
            )
        ) {
            if (selectedLocation != null) {
                Marker(
                    state = MarkerState(position = selectedLocation),
                    title = "Position sélectionnée",
                    snippet = "(${selectedLocation.latitude.format(2)}, ${selectedLocation.longitude.format(2)})"
                )
            }
        }
    }
}

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
    
    // Initialisation de l'API Places
    LaunchedEffect(Unit) {
        try {
            PlacesUtil.initialize(context)
            Log.d("AddTaskScreen", "API Places initialisée au démarrage")
        } catch (e: Exception) {
            Log.e("AddTaskScreen", "Erreur d'initialisation Places: ${e.message}")
            // Afficher un message d'erreur à l'utilisateur si nécessaire
        }
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
    var searchResults by remember { mutableStateOf<List<PlacesUtil.PlaceSearchResult>>(emptyList()) }
    var isSearching by remember { mutableStateOf(false) }
    var searchJob by remember { mutableStateOf<Job?>(null) }

    fun handlePlaceSearch(query: String) {
        searchQuery = query
        searchJob?.cancel()
                                 
        if (query.length >= 3) {
            isSearching = true
            searchJob = scope.launch {
                delay(500) // Délai pour éviter les requêtes excessives
                try {
                    try {
                        PlacesUtil.initialize(context)
                    } catch (e: Exception) {
                        Log.e("AddTaskScreen", "Échec de réinitialisation Places: ${e.message}")
                    }
                    
                    Log.d("AddTaskScreen", "Recherche lancée pour: $query")
                    val results = PlacesUtil.searchPlaces(query, context)
                    
                    if (results.isNotEmpty()) {
                        Log.d("AddTaskScreen", "Résultats trouvés: ${results.size}")
                        searchResults = results
                    } else {
                        Log.e("AddTaskScreen", "Aucun résultat trouvé pour: $query")
                        searchResults = emptyList()
                    }
                } catch (e: Exception) {
                    Log.e("AddTaskScreen", "Erreur de recherche Places: ${e.message}")
                    e.printStackTrace()
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
    }

    fun handlePlaceSelection(result: PlacesUtil.PlaceSearchResult) {
        scope.launch {
            try {
                if (result.placeId.startsWith("error_fallback")) {
                    selectedLocation = LatLng(48.427362, -71.067948) // Chicoutimi par défaut
                } else {
                    val placeDetails = PlacesUtil.getPlaceDetails(result.placeId, context)
                    selectedLocation = placeDetails.latLng
                }
                searchQuery = result.fullText
                searchResults = emptyList()
            } catch (e: Exception) {
                Log.e("AddTaskScreen", "Erreur lors de la récupération des détails du lieu: ${e.message}")
            }
        }
    }

    fun handleLocationToggle(enabled: Boolean) {
        if (enabled) {
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
            // Titre et progression
            TaskTitleField(
                title = title,
                onTitleChange = { title = it },
                progress = progress,
                total = total
            )

            // Description
            TaskDescriptionField(
                description = description,
                onDescriptionChange = { description = it }
            )

            // Date et heure
            DateTimeSelector(
                selectedDate = selectedDate,
                selectedTime = selectedTime,
                onDateClick = { calendarState.show() },
                onTimeClick = { clockState.show() }
            )

            // Priorité
            PrioritySelector(
                selectedPriority = priority,
                onPrioritySelected = { priority = it }
            )

            // Catégorie
            CategorySelector(
                selectedCategory = selectedCategory,
                onCategoryClick = { showCategoryDialog = true }
            )

            // Projet
            ProjectSelector(
                selectedProject = selectedProject,
                onProjectClick = { showProjectDialog = true }
            )

            // Localisation (déjà optimisé)
            if (useLocation) {
                Spacer(modifier = Modifier.height(16.dp))
                
                LocationSearchSection(
                    useLocation = useLocation,
                    searchQuery = searchQuery,
                    onSearchQueryChange = { query -> handlePlaceSearch(query) },
                    isSearching = isSearching,
                    searchResults = searchResults,
                    onResultClick = { result -> handlePlaceSelection(result) },
                    onToggleLocation = { enabled -> handleLocationToggle(enabled) }
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Carte
                MapView(
                    selectedLocation = selectedLocation,
                    onMapClick = { latLng ->
                        selectedLocation = latLng
                        searchQuery = "Position sélectionnée (${latLng.latitude.format(2)}, ${latLng.longitude.format(2)})"
                    }
                )
                
                // Rayon de localisation
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Notification radius: ${locationRadius.toInt()} meters",
                    style = MaterialTheme.typography.bodyMedium
                )
                Slider(
                    value = locationRadius,
                    onValueChange = { locationRadius = it },
                    valueRange = 50f..1000f,
                    steps = 19,
                    modifier = Modifier.fillMaxWidth(),
                    colors = SliderDefaults.colors(
                        thumbColor = Color(0xFF613BE7),
                        activeTrackColor = Color(0xFF613BE7)
                    )
                )
            } else {
                // Bouton pour activer la localisation
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { handleLocationToggle(true) },
                    colors = CardDefaults.outlinedCardColors(
                        containerColor = Color(0xFFF8F7FC)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = Color(0xFF613BE7)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Add Location Reminder",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.SemiBold
                                )
                            )
                            Text(
                                text = "Get notified when you are near this location",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Bouton de création de tâche
            CreateTaskButton {
                // Créer la tâche
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
                // Naviguer vers l'écran précédent
                navController.navigateUp()
            }

            Spacer(modifier = Modifier.height(80.dp))
        }
    }

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