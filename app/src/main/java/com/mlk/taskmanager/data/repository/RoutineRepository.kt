package com.mlk.taskmanager.data.repository

import com.mlk.taskmanager.data.dao.RoutineDao
import com.mlk.taskmanager.data.model.Routine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.time.DayOfWeek
import javax.inject.Inject
import javax.inject.Singleton

interface RoutineRepository {
    fun getAllRoutines(): Flow<List<Routine>>
    fun getActiveRoutines(): Flow<List<Routine>>
    fun getLocationBasedRoutines(): Flow<List<Routine>>
    fun getRoutinesForDay(dayOfWeek: DayOfWeek): Flow<List<Routine>>
    fun getRoutineById(id: Long): Flow<Routine?>
    suspend fun insertRoutine(routine: Routine): Long
    suspend fun updateRoutine(routine: Routine)
    suspend fun deleteRoutine(routine: Routine)
}

@Singleton
class RoutineRepositoryImpl @Inject constructor(
    private val routineDao: RoutineDao
) : RoutineRepository {
    override fun getAllRoutines(): Flow<List<Routine>> = routineDao.getAllRoutines()
    
    override fun getActiveRoutines(): Flow<List<Routine>> = routineDao.getActiveRoutines()
    
    override fun getLocationBasedRoutines(): Flow<List<Routine>> = routineDao.getLocationBasedRoutines()
    
    override fun getRoutinesForDay(dayOfWeek: DayOfWeek): Flow<List<Routine>> =
        routineDao.getActiveRoutinesForFiltering().map { routines ->
            routines.filter { routine ->
                routine.repeatDays.contains(dayOfWeek)
            }
        }
    
    override fun getRoutineById(id: Long): Flow<Routine?> = flow {
        emit(routineDao.getRoutineById(id))
    }
    
    override suspend fun insertRoutine(routine: Routine): Long = routineDao.insertRoutine(routine)
    
    override suspend fun updateRoutine(routine: Routine) = routineDao.updateRoutine(routine)
    
    override suspend fun deleteRoutine(routine: Routine) = routineDao.deleteRoutine(routine)
} 