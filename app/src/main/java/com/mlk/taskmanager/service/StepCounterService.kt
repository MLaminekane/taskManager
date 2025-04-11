package com.mlk.taskmanager.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Binder
import android.os.IBinder
import android.util.Log

/**
 * Service de détection et comptage des pas utilisant le gyroscope
 * Détecte les mouvements caractéristiques de la marche pour compter les pas
 */
class StepCounterService : Service(), SensorEventListener {

    companion object {
        private const val TAG = "StepCounterService"
        private const val STEP_THRESHOLD = 9.0f // Seuil de détection de pas
        private const val STEP_LENGTH = 0.7f // Longueur moyenne d'un pas en mètres
    }

    private lateinit var sensorManager: SensorManager
    private val binder = StepCounterBinder()
    private var gyroscope: Sensor? = null

    private var stepCount = 0
    private var lastY = 0f
    private var isMoving = false
    
    // Indique si la détection est active
    private var isTracking = false

    /**
     * Classe Binder qui permet au client de communiquer avec le service
     */
    inner class StepCounterBinder : Binder() {
        fun getService(): StepCounterService = this@StepCounterService
    }

    /**
     * Initialisation du service et des capteurs
     */
    override fun onCreate() {
        super.onCreate()
        sensorManager = applicationContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        if (gyroscope == null) {
            Log.e(TAG, "Gyroscope sensor not available")
        }
    }

    /**
     * Retourne le Binder lors de la connexion au service
     */
    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    /**
     * Démarrer la détection des pas
     * Active l'écoute du gyroscope
     */
    fun startTracking() {
        if (!isTracking && gyroscope != null) {
            sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL)
            isTracking = true
            Log.d(TAG, "Step tracking started, gyroscope sensor registered")
        }
    }

    /**
     * Arrêter la détection des pas
     * Désactive l'écoute du gyroscope
     */
    fun stopTracking() {
        if (isTracking) {
            sensorManager.unregisterListener(this)
            isTracking = false
            Log.d(TAG, "Step tracking stopped, gyroscope sensor unregistered")
        }
    }

    /**
     * Traitement des données du capteur pour détecter les pas
     */
    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_GYROSCOPE) {
            val y = event.values[1] // Valeur Y du gyroscope

            // Détection de mouvement vertical caractéristique d'un pas
            if (Math.abs(y) > STEP_THRESHOLD) {
                if (!isMoving) {
                    isMoving = true
                    stepCount++
                    Log.d(TAG, "Step detected. Total steps: $stepCount")
                }
            } else {
                isMoving = false
            }

            lastY = y
        }
    }

    /**
     * Gestion des changements de précision du capteur
     */
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Non utilisé, mais requis par l'interface SensorEventListener
    }

    /**
     * Retourne le nombre actuel de pas
     */
    fun getStepCount(): Int = stepCount

    /**
     * Retourne la distance parcourue estimée en mètres
     * Basée sur une longueur moyenne de pas de 0.7m
     */
    fun getDistanceInMeters(): Float = stepCount * STEP_LENGTH

    /**
     * Réinitialise le compteur de pas
     */
    fun resetCounter() {
        stepCount = 0
        Log.d(TAG, "Step counter reset")
    }

    /**
     * Arrêt du service et des écouteurs de capteurs
     */
    override fun onDestroy() {
        super.onDestroy()
        stopTracking()
        Log.d(TAG, "Service destroyed")
    }
}