package com.mlk.taskmanager.data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.DayOfWeek

/**
 * Convertisseur Room pour les listes de DayOfWeek
 */
class DayOfWeekConverter {
    private val gson = Gson()
    
    @TypeConverter
    fun fromDayOfWeekList(value: List<DayOfWeek>?): String? {
        return if (value == null) {
            null
        } else {
            // Convertir les DayOfWeek en entiers pour la sérialisation
            val dayValues = value.map { it.value }
            gson.toJson(dayValues)
        }
    }

    @TypeConverter
    fun toDayOfWeekList(value: String?): List<DayOfWeek>? {
        return if (value == null) {
            null
        } else {
            // Convertir les entiers en DayOfWeek
            val type = object : TypeToken<List<Int>>() {}.type
            val dayValues: List<Int> = gson.fromJson(value, type)
            dayValues.map { DayOfWeek.of(it) }
        }
    }
} 