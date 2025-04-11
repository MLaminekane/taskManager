package com.mlk.taskmanager.service

import android.accounts.Account
import android.content.Context
import android.util.Log
import com.google.android.gms.auth.GoogleAuthUtil
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import com.google.api.client.util.DateTime
import com.google.api.services.calendar.Calendar
import com.google.api.services.calendar.CalendarScopes
import com.mlk.taskmanager.R
import com.mlk.taskmanager.data.model.Routine
import com.mlk.taskmanager.data.repository.RoutineRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import java.io.IOException
import java.time.ZoneId
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CalendarSyncService @Inject constructor(
    @ApplicationContext private val context: Context,
    private val routineRepository: RoutineRepository
) {
    private val tag = "CalendarSyncService"
    private var calendarService: Calendar? = null
    private var currentAccount: GoogleSignInAccount? = null

    suspend fun initializeCalendarService(signInAccount: GoogleSignInAccount) {
        try {
            Log.d(tag, "Initializing Calendar service for account: ${signInAccount.email}")
            
            val transport = GoogleNetHttpTransport.newTrustedTransport()
            val jsonFactory = GsonFactory.getDefaultInstance()
            
            val credential = GoogleAccountCredential.usingOAuth2(
                context,
                listOf(
                    CalendarScopes.CALENDAR,
                    CalendarScopes.CALENDAR_EVENTS
                )
            )

            // Vérifier que l'email n'est pas null
            val email = signInAccount.email ?: throw IllegalArgumentException("Email cannot be null")
            // Utiliser l'objet Account au lieu de juste l'email
            val account = Account(email, GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE)
            credential.selectedAccount = account
            
            Log.d(tag, "Creating Calendar service with credential")
            calendarService = Calendar.Builder(transport, jsonFactory, credential)
                .setApplicationName("TaskManager")
                .build()
                
            currentAccount = signInAccount
            
            Log.d(tag, "Calendar service initialized successfully")
        } catch (e: Exception) {
            Log.e(tag, "Error initializing calendar service", e)
            
            // Logging détaillé pour diagnostiquer l'erreur
            when (e) {
                is ApiException -> {
                    val statusCode = e.statusCode
                    val statusMessage = e.statusMessage ?: "Pas de message"
                    val errorDetails = "Code: $statusCode, Message: $statusMessage"
                    Log.e(tag, "API Exception details: $errorDetails", e)
                    
                    // Pour l'erreur 10 spécifiquement
                    if (statusCode == 10) {
                        Log.e(tag, "Erreur 10 - Requête malformée. Vérifiez les scopes et les paramètres", e)
                    }
                    
                    val errorMessage = when (e.statusCode) {
                        7 -> "Network error - Vérifiez votre connexion internet"
                        10 -> "Requête malformée - Vérifiez les scopes et les paramètres"
                        16 -> "Erreur d'authentification - Réessayez de vous connecter"
                        else -> "Erreur de connexion (${e.statusCode}): ${e.message}"
                    }
                    throw Exception(errorMessage)
                }
                else -> {
                    Log.e(tag, "Autre exception: ${e.javaClass.simpleName}: ${e.message}", e)
                    throw e
                }
            }
            
            calendarService = null
            currentAccount = null
        }
    }
    
    // Vérifier si l'utilisateur est connecté à Google
    fun isSignedIn(): Boolean {
        val account = GoogleSignIn.getLastSignedInAccount(context)
        val isValid = account != null && !account.isExpired
        Log.d(tag, "Checking if signed in: $isValid")
        return isValid
    }
    
    // Obtenir les options de connexion Google
    fun getGoogleSignInOptions(): GoogleSignInOptions {
        Log.d(tag, "Configuring Google Sign-In options")
        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestScopes(Scope(CalendarScopes.CALENDAR))
            // Pas de requestIdToken pour éviter les problèmes de configuration OAuth
            .build()
    }
    
    // Synchroniser une routine avec Google Calendar
    suspend fun syncRoutineWithCalendar(routine: Routine): Result<Routine> = withContext(Dispatchers.IO) {
        try {
            if (calendarService == null || currentAccount == null) {
                Log.e(tag, "Calendar service not initialized")
                return@withContext Result.failure(IllegalStateException("Calendar service not initialized"))
            }
            
            // Si la routine a déjà un ID d'événement, supprimer cet événement d'abord
            routine.calendarEventId?.let { eventId ->
                try {
                    calendarService?.events()?.delete("primary", eventId)?.execute()
                    Log.d(tag, "Deleted existing calendar event: $eventId")
                } catch (e: IOException) {
                    Log.e(tag, "Error deleting existing calendar event", e)
                }
            }
            
            // Créer un nouvel événement
            val event = com.google.api.services.calendar.model.Event()
                .setSummary(routine.title)
                .setDescription(routine.description)
            
            val startDateTime = DateTime(
                routine.time.atDate(java.time.LocalDate.now())
                    .atZone(ZoneId.systemDefault())
                    .toInstant()
                    .toEpochMilli()
            )
            
            val endDateTime = DateTime(
                routine.time.plusHours(1).atDate(java.time.LocalDate.now())
                    .atZone(ZoneId.systemDefault())
                    .toInstant()
                    .toEpochMilli()
            )
            
            // Configuration de début et fin de l'événement
            val start = com.google.api.services.calendar.model.EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone(ZoneId.systemDefault().id)
            
            val end = com.google.api.services.calendar.model.EventDateTime()
                .setDateTime(endDateTime)
                .setTimeZone(ZoneId.systemDefault().id)
            
            event.setStart(start)
            event.setEnd(end)
            
            // Configuration de la récurrence
            if (routine.repeatDays.isNotEmpty()) {
                val recurrence = mutableListOf<String>()
                val daysOfWeek = routine.repeatDays.joinToString(",") { day ->
                    when (day) {
                        java.time.DayOfWeek.MONDAY -> "MO"
                        java.time.DayOfWeek.TUESDAY -> "TU"
                        java.time.DayOfWeek.WEDNESDAY -> "WE"
                        java.time.DayOfWeek.THURSDAY -> "TH"
                        java.time.DayOfWeek.FRIDAY -> "FR"
                        java.time.DayOfWeek.SATURDAY -> "SA"
                        java.time.DayOfWeek.SUNDAY -> "SU"
                    }
                }
                recurrence.add("RRULE:FREQ=WEEKLY;BYDAY=$daysOfWeek")
                event.setRecurrence(recurrence)
            }
            
            // Créer l'événement dans Google Calendar
            val createdEvent = calendarService?.events()?.insert("primary", event)?.execute()
            
            if (createdEvent != null && createdEvent.id != null) {
                Log.d(tag, "Calendar event created: ${createdEvent.id}")
                
                // Mettre à jour la routine avec l'ID de l'événement
                val updatedRoutine = routine.copy(
                    calendarEventId = createdEvent.id,
                    isSyncedWithCalendar = true
                )
                
                // Enregistrer la routine mise à jour
                routineRepository.updateRoutine(updatedRoutine)
                
                return@withContext Result.success(updatedRoutine)
            } else {
                Log.e(tag, "Failed to create calendar event")
                return@withContext Result.failure(IOException("Failed to create calendar event"))
            }
        } catch (e: Exception) {
            Log.e(tag, "Error syncing routine with calendar", e)
            return@withContext Result.failure(e)
        }
    }
    
    // Mettre à jour un événement existant
    suspend fun updateCalendarEvent(routine: Routine): Result<Routine> = withContext(Dispatchers.IO) {
        // Si la routine n'a pas d'ID d'événement ou n'est pas synchronisée, créer un nouvel événement
        if (routine.calendarEventId == null || !routine.isSyncedWithCalendar) {
            return@withContext syncRoutineWithCalendar(routine)
        }
        
        try {
            // Déléguer à la synchronisation qui gère la mise à jour en supprimant et recréant
            syncRoutineWithCalendar(routine)
        } catch (e: Exception) {
            Log.e(tag, "Error updating calendar event", e)
            Result.failure(e)
        }
    }
    
    // Supprimer un événement du calendrier
    suspend fun deleteCalendarEvent(routine: Routine): Result<Boolean> = withContext(Dispatchers.IO) {
        try {
            if (calendarService == null || currentAccount == null) {
                Log.e(tag, "Calendar service not initialized")
                return@withContext Result.failure(IllegalStateException("Calendar service not initialized"))
            }
            
            // Vérifier si la routine a un ID d'événement
            if (routine.calendarEventId == null) {
                Log.d(tag, "No calendar event to delete")
                return@withContext Result.success(true)
            }
            
            // Supprimer l'événement de Google Calendar
            calendarService?.events()?.delete("primary", routine.calendarEventId)?.execute()
            
            // Mettre à jour la routine pour indiquer qu'elle n'est plus synchronisée
            val updatedRoutine = routine.copy(
                calendarEventId = null,
                isSyncedWithCalendar = false
            )
            
            // Enregistrer la routine mise à jour
            routineRepository.updateRoutine(updatedRoutine)
            
            return@withContext Result.success(true)
        } catch (e: Exception) {
            Log.e(tag, "Error deleting calendar event", e)
            return@withContext Result.failure(e)
        }
    }
    
    // Synchroniser toutes les routines actives
    suspend fun syncAllRoutines(): Result<Int> = withContext(Dispatchers.IO) {
        try {
            if (calendarService == null || currentAccount == null) {
                Log.e(tag, "Calendar service not initialized")
                return@withContext Result.failure(IllegalStateException("Calendar service not initialized"))
            }
            
            var successCount = 0
            val activeRoutines = routineRepository.getActiveRoutines().first()
            
            for (routine in activeRoutines) {
                try {
                    syncRoutineWithCalendar(routine).onSuccess {
                        successCount++
                    }
                } catch (e: Exception) {
                    Log.e(tag, "Error syncing routine: ${routine.id}", e)
                }
            }
            
            return@withContext Result.success(successCount)
        } catch (e: Exception) {
            Log.e(tag, "Error syncing all routines", e)
            return@withContext Result.failure(e)
        }
    }
} 