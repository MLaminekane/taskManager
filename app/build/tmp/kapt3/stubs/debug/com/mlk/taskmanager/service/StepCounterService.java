package com.mlk.taskmanager.service;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000  2\u00020\u00012\u00020\u0002:\u0002 !B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0006\u0010\u0010\u001a\u00020\u000bJ\u0006\u0010\u0011\u001a\u00020\u000fJ\u001a\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0015\u001a\u00020\u000fH\u0016J\u0012\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0013H\u0016J\b\u0010\u001b\u001a\u00020\u0013H\u0016J\u0010\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0006\u0010\u001f\u001a\u00020\u0013R\u0012\u0010\u0004\u001a\u00060\u0005R\u00020\u0000X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lcom/mlk/taskmanager/service/StepCounterService;", "Landroid/app/Service;", "Landroid/hardware/SensorEventListener;", "()V", "binder", "Lcom/mlk/taskmanager/service/StepCounterService$StepCounterBinder;", "gyroscope", "Landroid/hardware/Sensor;", "isMoving", "", "lastY", "", "sensorManager", "Landroid/hardware/SensorManager;", "stepCount", "", "getDistanceInMeters", "getStepCount", "onAccuracyChanged", "", "sensor", "accuracy", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "onSensorChanged", "event", "Landroid/hardware/SensorEvent;", "resetCounter", "Companion", "StepCounterBinder", "app_debug"})
public final class StepCounterService extends android.app.Service implements android.hardware.SensorEventListener {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "StepCounterService";
    private static final float STEP_THRESHOLD = 9.0F;
    private static final float STEP_LENGTH = 0.7F;
    private android.hardware.SensorManager sensorManager;
    @org.jetbrains.annotations.NotNull()
    private final com.mlk.taskmanager.service.StepCounterService.StepCounterBinder binder = null;
    @org.jetbrains.annotations.Nullable()
    private android.hardware.Sensor gyroscope;
    private int stepCount = 0;
    private float lastY = 0.0F;
    private boolean isMoving = false;
    @org.jetbrains.annotations.NotNull()
    public static final com.mlk.taskmanager.service.StepCounterService.Companion Companion = null;
    
    public StepCounterService() {
        super();
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.os.IBinder onBind(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent) {
        return null;
    }
    
    @java.lang.Override()
    public void onSensorChanged(@org.jetbrains.annotations.NotNull()
    android.hardware.SensorEvent event) {
    }
    
    @java.lang.Override()
    public void onAccuracyChanged(@org.jetbrains.annotations.Nullable()
    android.hardware.Sensor sensor, int accuracy) {
    }
    
    public final int getStepCount() {
        return 0;
    }
    
    public final float getDistanceInMeters() {
        return 0.0F;
    }
    
    public final void resetCounter() {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/mlk/taskmanager/service/StepCounterService$Companion;", "", "()V", "STEP_LENGTH", "", "STEP_THRESHOLD", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/mlk/taskmanager/service/StepCounterService$StepCounterBinder;", "Landroid/os/Binder;", "(Lcom/mlk/taskmanager/service/StepCounterService;)V", "getService", "Lcom/mlk/taskmanager/service/StepCounterService;", "app_debug"})
    public final class StepCounterBinder extends android.os.Binder {
        
        public StepCounterBinder() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.mlk.taskmanager.service.StepCounterService getService() {
            return null;
        }
    }
}