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

    inner class StepCounterBinder : Binder() {
        fun getService(): StepCounterService = this@StepCounterService
    }

    override fun onCreate() {
        super.onCreate()
        sensorManager = applicationContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        if (gyroscope != null) {
            sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL)
            Log.d(TAG, "Gyroscope sensor registered")
        } else {
            Log.e(TAG, "Gyroscope sensor not available")
        }
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_GYROSCOPE) {
            val y = event.values[1] // Valeur Y du gyroscope

            // Détection de mouvement vertical
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

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Non utilisé
    }

    fun getStepCount(): Int = stepCount

    fun getDistanceInMeters(): Float = stepCount * STEP_LENGTH

    fun resetCounter() {
        stepCount = 0
        Log.d(TAG, "Step counter reset")
    }

    override fun onDestroy() {
        super.onDestroy()
        sensorManager.unregisterListener(this)
        Log.d(TAG, "Service destroyed")
    }
}