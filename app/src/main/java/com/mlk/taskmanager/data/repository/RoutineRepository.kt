package com.mlk.taskmanager.data.repository

import com.mlk.taskmanager.data.dao.RoutineDao
import com.mlk.taskmanager.data.model.Routine
import kotlinx.coroutines.flow.Flow
import java.time.DayOfWeek
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoutineRepository @Inject constructor(
    private val routineDao: RoutineDao
) {
    fun getAllRoutines(): Flow<List<Routine>> = routineDao.getAllRoutines()
    
    fun getActiveRoutines(): Flow<List<Routine>> = routineDao.getActiveRoutines()
    
    fun getLocationBasedRoutines(): Flow<List<Routine>> = routineDao.getLocationBasedRoutines()
    
    fun getRoutinesForDay(dayOfWeek: DayOfWeek): Flow<List<Routine>> =
        routineDao.getRoutinesForDay(dayOfWeek)
    
    suspend fun getRoutineById(routineId: Long): Routine? = routineDao.getRoutineById(routineId)
    
    suspend fun insertRoutine(routine: Routine): Long = routineDao.insertRoutine(routine)
    
    suspend fun updateRoutine(routine: Routine) = routineDao.updateRoutine(routine)
    
    suspend fun deleteRoutine(routine: Routine) = routineDao.deleteRoutine(routine)
} 