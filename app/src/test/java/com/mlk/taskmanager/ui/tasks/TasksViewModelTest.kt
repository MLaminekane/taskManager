package com.mlk.taskmanager.ui.tasks

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.mlk.taskmanager.data.model.Priority
import com.mlk.taskmanager.data.model.Task
import com.mlk.taskmanager.data.repository.ProjectRepository
import com.mlk.taskmanager.data.repository.SettingsRepository
import com.mlk.taskmanager.data.repository.TaskRepository
import com.mlk.taskmanager.service.CalendarSyncService
import com.mlk.taskmanager.service.LocationReminderService
import com.mlk.taskmanager.service.NotificationManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*
import java.time.LocalDateTime

@OptIn(ExperimentalCoroutinesApi::class)
class TasksViewModelTest {
    
    private lateinit var taskRepository: TaskRepository
    private lateinit var projectRepository: ProjectRepository
    private lateinit var locationReminderService: LocationReminderService
    private lateinit var notificationManager: NotificationManager
    private lateinit var calendarSyncService: CalendarSyncService
    private lateinit var settingsRepository: SettingsRepository
    private lateinit var viewModel: TasksViewModel
    private val testDispatcher = UnconfinedTestDispatcher()
    
    private val tasksFlow = MutableStateFlow<List<Task>>(emptyList())
    
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        
        taskRepository = mock {
            on { getAllTasks() } doReturn tasksFlow
        }
        projectRepository = mock()
        locationReminderService = mock()
        notificationManager = mock()
        calendarSyncService = mock()
        settingsRepository = mock()
        
        viewModel = TasksViewModel(
            taskRepository = taskRepository,
            projectRepository = projectRepository,
            locationReminderService = locationReminderService,
            notificationManager = notificationManager,
            calendarSyncService = calendarSyncService,
            settingsRepository = settingsRepository
        )
    }
    
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    
    /**
     * Vérifie que l'état initial du ViewModel contient:
     * - Une liste de tâches vide
     * - Une liste de tâches filtrées vide
     * - L'indicateur de chargement à true
     * 
     * Ce test est important pour s'assurer que le ViewModel démarre 
     * dans un état cohérent et prévisible.
     */
    @Test
    fun `initial state should have empty tasks and isLoading true`() = runTest {
        // When initialized in setup()
        
        // Then
        val initialState = viewModel.uiState.value
        assertThat(initialState.tasks).isEmpty()
        assertThat(initialState.filteredTasks).isEmpty()
        assertThat(initialState.isLoading).isTrue()
    }
    
    /**
     * Vérifie que l'état UI du ViewModel est correctement mis à jour
     * lorsque des tâches sont chargées depuis le repository.
     * 
     * Le test confirme que:
     * - Les tâches sont correctement stockées dans l'état
     * - Les tâches filtrées sont identiques aux tâches non filtrées initialement
     * - L'indicateur de chargement passe à false une fois les données chargées
     */
    @Test
    fun `uiState should reflect loaded tasks`() = runTest {
        // Given
        val mockTasks = listOf(
            Task(
                id = 1,
                title = "Test Task 1",
                description = "Description 1",
                dueDateTime = LocalDateTime.now(),
                priority = Priority.HIGH
            ),
            Task(
                id = 2,
                title = "Test Task 2",
                description = "Description 2",
                dueDateTime = LocalDateTime.now().plusDays(1),
                priority = Priority.MEDIUM
            )
        )
        
        // When
        tasksFlow.update { mockTasks }
        
        // Then
        val state = viewModel.uiState.value
        assertThat(state.tasks).isEqualTo(mockTasks)
        assertThat(state.filteredTasks).isEqualTo(mockTasks)
        assertThat(state.isLoading).isFalse()
    }
    
    /**
     * Vérifie que la méthode addTask:
     * - Crée une tâche avec les paramètres fournis
     * - Appelle le repository pour insérer la tâche
     * - Programme des notifications pour la nouvelle tâche
     * 
     * Le test utilise des capteurs d'arguments pour vérifier les propriétés
     * exactes des objets transmis aux collaborateurs.
     */
    @Test
    fun `addTask should call repository and notificationManager`() = runTest {
        // Given
        val title = "New Task"
        val description = "Task Description"
        val dueDateTime = LocalDateTime.now().plusDays(1)
        val priority = Priority.MEDIUM
        val taskId = 3L
        
        // Use doReturn instead of whenever to avoid matcher issues
        doReturn(taskId).`when`(taskRepository).insertTask(
            argThat { task ->
                task.title == title &&
                task.description == description &&
                task.priority == priority
            }
        )
        
        // When
        viewModel.addTask(
            title = title,
            description = description,
            dueDateTime = dueDateTime,
            priority = priority
        )
        
        // Then
        val taskCaptor = argumentCaptor<Task>()
        verify(taskRepository).insertTask(taskCaptor.capture())
        
        val capturedTask = taskCaptor.firstValue
        assertThat(capturedTask.title).isEqualTo(title)
        assertThat(capturedTask.description).isEqualTo(description)
        assertThat(capturedTask.dueDateTime).isEqualTo(dueDateTime)
        assertThat(capturedTask.priority).isEqualTo(priority)
        
        val taskWithIdCaptor = argumentCaptor<Task>()
        verify(notificationManager).scheduleTaskNotifications(taskWithIdCaptor.capture())
        assertThat(taskWithIdCaptor.firstValue.id).isEqualTo(taskId)
    }
    
    /**
     * Vérifie que la méthode toggleTaskCompletion:
     * - Bascule correctement l'état d'achèvement d'une tâche (de false à true)
     * - Appelle le repository pour mettre à jour la tâche avec le nouvel état
     * 
     * Ce test est important pour confirmer la fonctionnalité de marquage
     * des tâches comme complétées.
     */
    @Test
    fun `toggleTaskCompletion should update task completion status`() = runTest {
        // Given
        val task = Task(
            id = 1,
            title = "Task to Toggle",
            description = "Description",
            dueDateTime = LocalDateTime.now(),
            priority = Priority.HIGH,
            isCompleted = false
        )
        
        // When
        viewModel.toggleTaskCompletion(task)
        
        // Then
        val taskCaptor = argumentCaptor<Task>()
        verify(taskRepository).updateTask(taskCaptor.capture())
        
        val updatedTask = taskCaptor.firstValue
        assertThat(updatedTask.id).isEqualTo(task.id)
        assertThat(updatedTask.isCompleted).isTrue()
    }
    
    /**
     * Vérifie que la méthode deleteTask:
     * - Appelle correctement le repository pour supprimer la tâche spécifiée
     * 
     * Ce test simple confirme que la délégation au repository fonctionne
     * correctement lors de la suppression d'une tâche.
     */
    @Test
    fun `deleteTask should call repository`() = runTest {
        // Given
        val task = Task(
            id = 1,
            title = "Task to Delete",
            description = "Description",
            dueDateTime = LocalDateTime.now(),
            priority = Priority.HIGH
        )
        
        // When
        viewModel.deleteTask(task)
        
        // Then
        verify(taskRepository).deleteTask(task)
    }
    
    /**
     * Vérifie que la méthode setSearchQuery:
     * - Met à jour la requête de recherche dans l'état UI
     * - Filtre correctement les tâches en fonction du texte recherché
     * - Trouve les correspondances à la fois dans le titre et la description
     * 
     * Ce test confirme que la fonctionnalité de recherche fonctionne comme prévu.
     */
    @Test
    fun `setSearchQuery should filter tasks`() = runTest {
        // Given
        val mockTasks = listOf(
            Task(
                id = 1,
                title = "Apple Task",
                description = "First task",
                dueDateTime = LocalDateTime.now(),
                priority = Priority.HIGH
            ),
            Task(
                id = 2,
                title = "Banana Task",
                description = "Second task",
                dueDateTime = LocalDateTime.now().plusDays(1),
                priority = Priority.MEDIUM
            ),
            Task(
                id = 3,
                title = "Cherry Task",
                description = "Apple in description",
                dueDateTime = LocalDateTime.now().plusDays(2),
                priority = Priority.LOW
            )
        )
        
        tasksFlow.update { mockTasks }
        
        // When
        viewModel.setSearchQuery("Apple")
        
        // Then
        viewModel.uiState.test {
            val state = awaitItem()
            assertThat(state.searchQuery).isEqualTo("Apple")
            assertThat(state.filteredTasks).hasSize(2)
            assertThat(state.filteredTasks[0].title).isEqualTo("Apple Task")
            assertThat(state.filteredTasks[1].title).isEqualTo("Cherry Task")
            cancelAndIgnoreRemainingEvents()
        }
    }
    
    /**
     * Vérifie que la méthode togglePriorityFilter:
     * - Ajoute ou supprime des priorités de la liste des filtres sélectionnés
     * - Met à jour correctement la liste des tâches filtrées en conséquence
     * 
     * Ce test montre que le filtrage par priorité fonctionne correctement quand
     * on bascule l'état d'un filtre.
     */
    @Test
    fun `togglePriorityFilter should update filtered tasks`() = runTest {
        // Given
        val mockTasks = listOf(
            Task(
                id = 1,
                title = "High Task",
                description = "Description",
                dueDateTime = LocalDateTime.now(),
                priority = Priority.HIGH
            ),
            Task(
                id = 2,
                title = "Medium Task",
                description = "Description",
                dueDateTime = LocalDateTime.now().plusDays(1),
                priority = Priority.MEDIUM
            ),
            Task(
                id = 3,
                title = "Low Task",
                description = "Description",
                dueDateTime = LocalDateTime.now().plusDays(2),
                priority = Priority.LOW
            )
        )
        
        tasksFlow.update { mockTasks }
        
        // When - Excluant HIGH
        viewModel.togglePriorityFilter(Priority.HIGH)
        
        // Then
        viewModel.uiState.test {
            val state = awaitItem()
            assertThat(state.selectedPriorities).doesNotContain(Priority.HIGH)
            assertThat(state.selectedPriorities).containsExactly(Priority.MEDIUM, Priority.LOW)
            assertThat(state.filteredTasks).hasSize(2)
            assertThat(state.filteredTasks.map { it.title }).containsExactly("Medium Task", "Low Task")
            cancelAndIgnoreRemainingEvents()
        }
    }
    
    /**
     * Vérifie que la méthode setSortOption:
     * - Change l'option de tri dans l'état UI
     * - Réorganise correctement les tâches selon le critère de tri sélectionné
     * 
     * Ce test confirme que:
     * - Le tri par date (défaut) fonctionne correctement
     * - Le changement vers un tri par titre fonctionne également
     */
    @Test
    fun `setSortOption should sort filtered tasks`() = runTest {
        // Given - tasks avec dates différentes
        val now = LocalDateTime.now()
        val mockTasks = listOf(
            Task(
                id = 1,
                title = "C Task",
                description = "Description",
                dueDateTime = now.plusDays(2),
                priority = Priority.LOW
            ),
            Task(
                id = 2,
                title = "A Task",
                description = "Description",
                dueDateTime = now,
                priority = Priority.HIGH
            ),
            Task(
                id = 3,
                title = "B Task",
                description = "Description",
                dueDateTime = now.plusDays(1),
                priority = Priority.MEDIUM
            )
        )
        
        tasksFlow.update { mockTasks }
        
        // When - Tri par date ascendante (défaut)
        // Le tri par date est déjà appliqué comme par défaut
        
        // Then - Vérifier le tri par date ascendante
        assertThat(viewModel.uiState.value.filteredTasks.map { it.title })
            .containsExactly("A Task", "B Task", "C Task").inOrder()
        
        // When - Modifier pour trier par titre
        viewModel.setSortOption(SortOption.TITLE_DESC)
        
        // Then - Vérifier le tri par titre descendant
        assertThat(viewModel.uiState.value.filteredTasks.map { it.title })
            .containsExactly("C Task", "B Task", "A Task").inOrder()
    }
    
    /**
     * Vérifie que la méthode toggleShowCompletedTasks:
     * - Bascule l'état de visibilité des tâches complétées
     * - Filtre correctement la liste des tâches pour inclure ou exclure
     *   les tâches complétées selon l'état du filtre
     * 
     * Ce test est crucial pour valider le bon fonctionnement du filtre
     * d'affichage ou de masquage des tâches terminées.
     */
    @Test
    fun `toggleShowCompletedTasks should update filtered tasks`() = runTest {
        // Given
        val mockTasks = listOf(
            Task(
                id = 1,
                title = "Task 1",
                description = "Description",
                dueDateTime = LocalDateTime.now(),
                priority = Priority.HIGH,
                isCompleted = true
            ),
            Task(
                id = 2,
                title = "Task 2",
                description = "Description",
                dueDateTime = LocalDateTime.now().plusDays(1),
                priority = Priority.MEDIUM,
                isCompleted = false
            )
        )
        
        tasksFlow.update { mockTasks }
        
        // When - Par défaut, montrer les tâches complétées
        assertThat(viewModel.uiState.value.showCompletedTasks).isTrue()
        assertThat(viewModel.uiState.value.filteredTasks).hasSize(2)
        
        // When - Masquer les tâches complétées
        viewModel.toggleShowCompletedTasks()
        
        // Then
        assertThat(viewModel.uiState.value.showCompletedTasks).isFalse()
        assertThat(viewModel.uiState.value.filteredTasks).hasSize(1)
        assertThat(viewModel.uiState.value.filteredTasks[0].title).isEqualTo("Task 2")
    }
} 
