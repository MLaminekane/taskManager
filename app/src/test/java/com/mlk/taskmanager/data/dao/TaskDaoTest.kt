package com.mlk.taskmanager.data.dao

import com.google.common.truth.Truth.assertThat
import com.mlk.taskmanager.data.model.Priority
import com.mlk.taskmanager.data.model.Task
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*
import java.time.LocalDateTime

class TaskDaoTest {
    
    private lateinit var taskDao: TaskDao
    
    @Before
    fun setup() {
        taskDao = mock()
    }
    
    @Test
    fun `insertTask should return id`() = runTest {
        // Given
        val task = Task(
            title = "Test Task",
            description = "Description",
            dueDateTime = LocalDateTime.now(),
            priority = Priority.HIGH
        )
        val expectedId = 1L
        whenever(taskDao.insertTask(any())).thenReturn(expectedId)
        
        // When
        val id = taskDao.insertTask(task)
        
        // Then
        assertThat(id).isEqualTo(expectedId)
        verify(taskDao).insertTask(task)
    }
    
    @Test
    fun `getAllTasks should return flow of tasks`() = runTest {
        // Given
        val now = LocalDateTime.now()
        val mockTasks = listOf(
            Task(
                id = 1,
                title = "Task 1",
                description = "Description 1",
                dueDateTime = now.plusDays(2),
                priority = Priority.MEDIUM
            ),
            Task(
                id = 2,
                title = "Task 2",
                description = "Description 2",
                dueDateTime = now,
                priority = Priority.HIGH
            )
        )
        
        whenever(taskDao.getAllTasks()).thenReturn(flowOf(mockTasks))
        
        // When
        val tasksFlow = taskDao.getAllTasks()
        
        // Then
        tasksFlow.collect { tasks ->
            assertThat(tasks).hasSize(2)
            assertThat(tasks).isEqualTo(mockTasks)
        }
    }
    
    @Test
    fun `getTaskById should return task with specified id`() = runTest {
        // Given
        val taskId = 5L
        val expectedTask = Task(
            id = taskId,
            title = "Task by ID",
            description = "Description",
            dueDateTime = LocalDateTime.now(),
            priority = Priority.HIGH
        )
        
        whenever(taskDao.getTaskById(taskId)).thenReturn(expectedTask)
        
        // When
        val result = taskDao.getTaskById(taskId)
        
        // Then
        assertThat(result).isEqualTo(expectedTask)
        verify(taskDao).getTaskById(taskId)
    }
    
    @Test
    fun `updateTask should call dao update method`() = runTest {
        // Given
        val task = Task(
            id = 1,
            title = "Updated Task",
            description = "Updated Description",
            dueDateTime = LocalDateTime.now(),
            priority = Priority.HIGH
        )
        
        doNothing().whenever(taskDao).updateTask(any())
        
        // When
        taskDao.updateTask(task)
        
        // Then
        verify(taskDao).updateTask(task)
    }
    
    @Test
    fun `deleteTask should call dao delete method`() = runTest {
        // Given
        val task = Task(
            id = 1,
            title = "Task to Delete",
            description = "Will be deleted",
            dueDateTime = LocalDateTime.now(),
            priority = Priority.LOW
        )
        
        doNothing().whenever(taskDao).deleteTask(any())
        
        // When
        taskDao.deleteTask(task)
        
        // Then
        verify(taskDao).deleteTask(task)
    }
    
    @Test
    fun `getActiveTasks should return non-completed tasks`() = runTest {
        // Given
        val mockActiveTasks = listOf(
            Task(
                id = 1,
                title = "Active Task 1",
                description = "Active task description 1",
                dueDateTime = LocalDateTime.now(),
                priority = Priority.HIGH,
                isCompleted = false
            ),
            Task(
                id = 2,
                title = "Active Task 2",
                description = "Active task description 2",
                dueDateTime = LocalDateTime.now().plusDays(1),
                priority = Priority.MEDIUM,
                isCompleted = false
            )
        )
        
        whenever(taskDao.getActiveTasks()).thenReturn(flowOf(mockActiveTasks))
        
        // When
        val activeTasks = taskDao.getActiveTasks()
        
        // Then
        activeTasks.collect { tasks ->
            assertThat(tasks).hasSize(2)
            assertThat(tasks.all { !it.isCompleted }).isTrue()
        }
    }
    
    @Test
    fun `getAllTasksSync should return tasks synchronously`() = runTest {
        // Given
        val mockTasks = listOf(
            Task(
                id = 1,
                title = "Task 1",
                description = "Task description 1",
                dueDateTime = LocalDateTime.now(),
                priority = Priority.HIGH
            ),
            Task(
                id = 2,
                title = "Task 2",
                description = "Task description 2",
                dueDateTime = LocalDateTime.now().plusDays(1),
                priority = Priority.MEDIUM
            )
        )
        
        whenever(taskDao.getAllTasksSync()).thenReturn(mockTasks)
        
        // When
        val result = taskDao.getAllTasksSync()
        
        // Then
        assertThat(result).isEqualTo(mockTasks)
        verify(taskDao).getAllTasksSync()
    }
} 