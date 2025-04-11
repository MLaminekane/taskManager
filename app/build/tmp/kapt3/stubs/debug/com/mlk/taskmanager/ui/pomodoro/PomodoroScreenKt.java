package com.mlk.taskmanager.ui.pomodoro;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00004\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a:\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0003\u001a\u001a\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0007\u001a4\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00032\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0003\u001a \u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0003\u001a \u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u0003H\u0003\u001a\u0018\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001dH\u0003\u00a8\u0006\u001f"}, d2 = {"PomodoroControls", "", "isRunning", "", "onStartPause", "Lkotlin/Function0;", "onReset", "onSkip", "PomodoroScreen", "navController", "Landroidx/navigation/NavController;", "viewModel", "Lcom/mlk/taskmanager/ui/pomodoro/PomodoroViewModel;", "PomodoroSettings", "dndEnabled", "notificationBlocked", "onDndToggle", "onNotificationBlockToggle", "PomodoroStatistics", "completedSessions", "", "totalFocusMinutes", "focusRate", "PomodoroTimer", "minutes", "seconds", "isBreak", "StatisticItem", "title", "", "value", "app_debug"})
public final class PomodoroScreenKt {
    
    /**
     * Écran Pomodoro
     * Permet à l'utilisateur de gérer des sessions de travail/pause selon la technique Pomodoro
     *
     * @param navController Contrôleur de navigation
     * @param viewModel ViewModel de gestion du Pomodoro
     */
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void PomodoroScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.ui.pomodoro.PomodoroViewModel viewModel) {
    }
    
    /**
     * Composant d'affichage du minuteur
     *
     * @param minutes Minutes restantes
     * @param seconds Secondes restantes
     * @param isBreak Indique si c'est une période de pause
     */
    @androidx.compose.runtime.Composable()
    private static final void PomodoroTimer(int minutes, int seconds, boolean isBreak) {
    }
    
    /**
     * Boutons de contrôle du minuteur
     *
     * @param isRunning Indique si le minuteur est en cours
     * @param onStartPause Action pour démarrer/mettre en pause le minuteur
     * @param onReset Action pour réinitialiser le minuteur
     * @param onSkip Action pour passer à la session suivante
     */
    @androidx.compose.runtime.Composable()
    private static final void PomodoroControls(boolean isRunning, kotlin.jvm.functions.Function0<kotlin.Unit> onStartPause, kotlin.jvm.functions.Function0<kotlin.Unit> onReset, kotlin.jvm.functions.Function0<kotlin.Unit> onSkip) {
    }
    
    /**
     * Carte des paramètres du mode Pomodoro
     *
     * @param dndEnabled État du mode Ne pas déranger
     * @param notificationBlocked État du blocage des notifications
     * @param onDndToggle Action pour activer/désactiver le mode Ne pas déranger
     * @param onNotificationBlockToggle Action pour activer/désactiver le blocage des notifications
     */
    @androidx.compose.runtime.Composable()
    private static final void PomodoroSettings(boolean dndEnabled, boolean notificationBlocked, kotlin.jvm.functions.Function0<kotlin.Unit> onDndToggle, kotlin.jvm.functions.Function0<kotlin.Unit> onNotificationBlockToggle) {
    }
    
    /**
     * Carte des statistiques du mode Pomodoro
     *
     * @param completedSessions Nombre de sessions terminées
     * @param totalFocusMinutes Nombre total de minutes de focus
     * @param focusRate Taux de concentration en pourcentage
     */
    @androidx.compose.runtime.Composable()
    private static final void PomodoroStatistics(int completedSessions, int totalFocusMinutes, int focusRate) {
    }
    
    /**
     * Élément individuel de statistique
     *
     * @param title Titre de la statistique
     * @param value Valeur de la statistique
     */
    @androidx.compose.runtime.Composable()
    private static final void StatisticItem(java.lang.String title, java.lang.String value) {
    }
}