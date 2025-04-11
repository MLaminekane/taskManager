package com.mlk.taskmanager.ui.pomodoro;

/**
 * ViewModel pour la technique Pomodoro
 * Gère le minuteur, les périodes de travail/pause et les statistiques
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\b\u0007\u0018\u0000 -2\u00020\u0001:\u0001-B!\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0018\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u0011H\u0002J\b\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u001dH\u0002J\b\u0010\u001f\u001a\u00020\u001dH\u0002J\b\u0010 \u001a\u0004\u0018\u00010!J\b\u0010\"\u001a\u00020\u001dH\u0014J\u0006\u0010#\u001a\u00020\u001dJ\u0006\u0010$\u001a\u00020\u001dJ\u0006\u0010%\u001a\u00020\u001dJ\u0006\u0010&\u001a\u00020\u001dJ\u0010\u0010\'\u001a\u00020\u001d2\u0006\u0010(\u001a\u00020\u0011H\u0002J\u0006\u0010)\u001a\u00020\u001dJ\u0006\u0010*\u001a\u00020\u001dJ\u0006\u0010+\u001a\u00020\u001dJ\b\u0010,\u001a\u00020\u001dH\u0002R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018\u00a8\u0006."}, d2 = {"Lcom/mlk/taskmanager/ui/pomodoro/PomodoroViewModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "notificationManager", "Lcom/mlk/taskmanager/service/NotificationManager;", "androidNotificationManager", "Landroid/app/NotificationManager;", "(Landroid/content/Context;Lcom/mlk/taskmanager/service/NotificationManager;Landroid/app/NotificationManager;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/mlk/taskmanager/ui/pomodoro/PomodoroUiState;", "audioManager", "Landroid/media/AudioManager;", "notificationManagerCompat", "Landroidx/core/app/NotificationManagerCompat;", "previousInterruptionFilter", "", "previousRingerMode", "timer", "Landroid/os/CountDownTimer;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "calculateFocusRate", "sessions", "totalMinutes", "checkNotificationPolicyAccess", "", "disableDoNotDisturb", "enableDoNotDisturb", "getNotificationPolicyAccessIntent", "Landroid/content/Intent;", "onCleared", "pauseTimer", "permissionDialogShown", "resetTimer", "skipSession", "startCountdown", "minutes", "startTimer", "toggleDnd", "toggleNotificationBlocking", "updateNotificationSettings", "Companion", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class PomodoroViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.mlk.taskmanager.service.NotificationManager notificationManager = null;
    @org.jetbrains.annotations.NotNull()
    private final android.app.NotificationManager androidNotificationManager = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "PomodoroViewModel";
    private static final int FOCUS_DURATION_MINUTES = 25;
    private static final int BREAK_DURATION_MINUTES = 5;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.mlk.taskmanager.ui.pomodoro.PomodoroUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.mlk.taskmanager.ui.pomodoro.PomodoroUiState> uiState = null;
    @org.jetbrains.annotations.Nullable()
    private android.os.CountDownTimer timer;
    @org.jetbrains.annotations.NotNull()
    private final android.media.AudioManager audioManager = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.core.app.NotificationManagerCompat notificationManagerCompat = null;
    private int previousRingerMode = android.media.AudioManager.RINGER_MODE_NORMAL;
    private int previousInterruptionFilter = android.app.NotificationManager.INTERRUPTION_FILTER_ALL;
    @org.jetbrains.annotations.NotNull()
    public static final com.mlk.taskmanager.ui.pomodoro.PomodoroViewModel.Companion Companion = null;
    
    @javax.inject.Inject()
    public PomodoroViewModel(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.service.NotificationManager notificationManager, @org.jetbrains.annotations.NotNull()
    android.app.NotificationManager androidNotificationManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.mlk.taskmanager.ui.pomodoro.PomodoroUiState> getUiState() {
        return null;
    }
    
    /**
     * Vérifie si l'application a l'autorisation de modifier la politique de notification
     * et met à jour l'état en conséquence
     */
    private final void checkNotificationPolicyAccess() {
    }
    
    /**
     * Ouvre les paramètres système pour demander l'accès à la politique de notification
     * Retourne un Intent que l'activité doit démarrer
     */
    @org.jetbrains.annotations.Nullable()
    public final android.content.Intent getNotificationPolicyAccessIntent() {
        return null;
    }
    
    /**
     * Active le mode Ne pas déranger du système
     * Utilise toutes les approches possibles selon la version d'Android
     */
    private final void enableDoNotDisturb() {
    }
    
    /**
     * Désactive le mode Ne pas déranger et restaure les paramètres précédents
     */
    private final void disableDoNotDisturb() {
    }
    
    /**
     * Démarre le compte à rebours pour une durée donnée
     * @param minutes Durée en minutes du compte à rebours
     */
    private final void startCountdown(int minutes) {
    }
    
    /**
     * Calcule le taux de concentration en pourcentage
     * @param sessions Nombre de sessions complétées
     * @param totalMinutes Nombre total de minutes de concentration
     * @return Taux de concentration en pourcentage
     */
    private final int calculateFocusRate(int sessions, int totalMinutes) {
        return 0;
    }
    
    /**
     * Démarre le minuteur
     */
    public final void startTimer() {
    }
    
    /**
     * Met en pause le minuteur
     */
    public final void pauseTimer() {
    }
    
    /**
     * Réinitialise le minuteur à la durée par défaut
     */
    public final void resetTimer() {
    }
    
    /**
     * Passe à la session suivante (de travail à pause ou inversement)
     */
    public final void skipSession() {
    }
    
    /**
     * Active/désactive le mode "Ne pas déranger"
     * Si la permission n'est pas accordée, affiche une demande de permission
     */
    public final void toggleDnd() {
    }
    
    /**
     * Indique que l'utilisateur a été informé de la nécessité d'une permission
     */
    public final void permissionDialogShown() {
    }
    
    /**
     * Active/désactive le blocage des notifications
     */
    public final void toggleNotificationBlocking() {
    }
    
    /**
     * Met à jour les paramètres de notification en fonction de l'état actuel
     */
    private final void updateNotificationSettings() {
    }
    
    /**
     * Nettoyage lors de la destruction du ViewModel
     */
    @java.lang.Override()
    protected void onCleared() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/mlk/taskmanager/ui/pomodoro/PomodoroViewModel$Companion;", "", "()V", "BREAK_DURATION_MINUTES", "", "FOCUS_DURATION_MINUTES", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}