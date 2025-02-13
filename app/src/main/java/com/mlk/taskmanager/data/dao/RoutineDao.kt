package com.mlk.taskmanager.data.dao

import androidx.room.*
import com.mlk.taskmanager.data.model.Routine
import kotlinx.coroutines.flow.Flow
import java.time.DayOfWeek

@Dao
interface RoutineDao {
    @Query("SELECT * FROM routines ORDER BY time ASC")
    fun getAllRoutines(): Flow<List<Routine>>
    
    @Query("SELECT * FROM routines WHERE isEnabled = 1 ORDER BY time ASC")
    fun getActiveRoutines(): Flow<List<Routine>>
    
    @Query("SELECT * FROM routines WHERE id = :routineId")
    suspend fun getRoutineById(routineId: Long): Routine?
    
    @Query("""
        SELECT * FROM routines 
        WHERE isEnabled = 1 
        AND latitude IS NOT NULL 
        AND longitude IS NOT NULL
        ORDER BY time ASC
    """)
    fun getLocationBasedRoutines(): Flow<List<Routine>>
    
    @Insert
    suspend fun insertRoutine(routine: Routine): Long
    
    @Update
    suspend fun updateRoutine(routine: Routine)
    
    @Delete
    suspend fun deleteRoutine(routine: Routine)
    
    @Query("SELECT * FROM routines WHERE :dayOfWeek IN (repeatDays) AND isEnabled = 1")
    fun getRoutinesForDay(dayOfWeek: DayOfWeek): Flow<List<Routine>>
} 