package com.mlk.taskmanager.data.repository

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.mlk.taskmanager.data.dao.TaskDao
import com.mlk.taskmanager.data.model.Priority
import com.mlk.taskmanager.data.model.Task
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.time.LocalDateTime

class TaskRepositoryTest {
    
    private lateinit var taskDao: TaskDao
    private lateinit var projectRepository: ProjectRepository
    private lateinit var taskRepository: TaskRepositoryImpl
    
    @Before
    fun setup() {
        taskDao = mock()
        projectRepository = mock()
        taskRepository = TaskRepositoryImpl(taskDao, projectRepository)
    }
    
    @Test
    fun `getAllTasks should return flow of tasks from DAO`() = runTest {
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
        whenever(taskDao.getAllTasks()).thenReturn(flowOf(mockTasks))
        
        // When & Then
        taskRepository.getAllTasks().test {
            val items = awaitItem()
            assertThat(items).isEqualTo(mockTasks)
            assertThat(items.size).isEqualTo(2)
            assertThat(items[0].title).isEqualTo("Test Task 1")
            assertThat(items[1].title).isEqualTo("Test Task 2")
            awaitComplete()
        }
    }
    
    @Test
    fun `insertTask should call DAO insert method`() = runTest {
        // Given
        val task = Task(
            title = "New Task",
            description = "New Description",
            dueDateTime = LocalDateTime.now(),
            priority = Priority.LOW
        )
        val expectedId = 3L
        whenever(taskDao.insertTask(any())).thenReturn(expectedId)
        
        // When
        val resultId = taskRepository.insertTask(task)
        
        // Then
        verify(taskDao).insertTask(task)
        assertThat(resultId).isEqualTo(expectedId)
    }
    
    @Test
    fun `insertTask should update project task count when projectId is not null`() = runTest {
        // Given
        val projectId = 5L
        val task = Task(
            title = "Project Task",
            description = "Task in a project",
            dueDateTime = LocalDateTime.now(),
            priority = Priority.MEDIUM,
            projectId = projectId
        )
        whenever(taskDao.insertTask(any())).thenReturn(1L)
        
        // When
        taskRepository.insertTask(task)
        
        // Then
        verify(projectRepository).incrementTaskCount(projectId)
    }
    
    @Test
    fun `updateTask should call DAO update method`() = runTest {
        // Given
        val task = Task(
            id = 1,
            title = "Updated Task",
            description = "Updated Description",
            dueDateTime = LocalDateTime.now(),
            priority = Priority.HIGH
        )
        
        val originalTask = Task(
            id = 1,
            title = "Original Task",
            description = "Original Description",
            dueDateTime = LocalDateTime.now(),
            priority = Priority.LOW
        )
        
        whenever(taskDao.getTaskById(1)).thenReturn(originalTask)
        
        // When
        taskRepository.updateTask(task)
        
        // Then
        verify(taskDao).updateTask(task)
    }
    
    @Test
    fun `updateTask should update project counters when projectId changes`() = runTest {
        // Given
        val oldProjectId = 5L
        val newProjectId = 10L
        
        val originalTask = Task(
            id = 1,
            title = "Original Task",
            description = "Original Description",
            dueDateTime = LocalDateTime.now(),
            priority = Priority.LOW,
            projectId = oldProjectId
        )
        
        val updatedTask = Task(
            id = 1,
            title = "Updated Task",
            description = "Updated Description",
            dueDateTime = LocalDateTime.now(),
            priority = Priority.HIGH,
            projectId = newProjectId
        )
        
        whenever(taskDao.getTaskById(1)).thenReturn(originalTask)
        
        // When
        taskRepository.updateTask(updatedTask)
        
        // Then
        verify(projectRepository).decrementTaskCount(oldProjectId)
        verify(projectRepository).incrementTaskCount(newProjectId)
    }
    
    @Test
    fun `deleteTask should call DAO delete method`() = runTest {
        // Given
        val task = Task(
            id = 1,
            title = "Task to Delete",
            description = "Will be deleted",
            dueDateTime = LocalDateTime.now(),
            priority = Priority.HIGH
        )
        
        // When
        taskRepository.deleteTask(task)
        
        // Then
        verify(taskDao).deleteTask(task)
    }
    
    @Test
    fun `deleteTask should decrement project task count when projectId is not null`() = runTest {
        // Given
        val projectId = 5L
        val task = Task(
            id = 1,
            title = "Project Task to Delete",
            description = "Will be deleted",
            dueDateTime = LocalDateTime.now(),
            priority = Priority.HIGH,
            projectId = projectId
        )
        
        // When
        taskRepository.deleteTask(task)
        
        // Then
        verify(projectRepository).decrementTaskCount(projectId)
    }
    
    @Test
    fun `getAllTasksSync should return list of tasks from DAO`() = runTest {
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
        whenever(taskDao.getAllTasksSync()).thenReturn(mockTasks)
        
        // When
        val result = taskRepository.getAllTasksSync()
        
        // Then
        assertThat(result).isEqualTo(mockTasks)
        assertThat(result.size).isEqualTo(2)
        verify(taskDao).getAllTasksSync()
    }
} 