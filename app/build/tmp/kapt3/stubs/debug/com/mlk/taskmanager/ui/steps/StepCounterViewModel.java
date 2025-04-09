package com.mlk.taskmanager.ui.steps;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0012\u001a\u00020\u0013H\u0014J\u0006\u0010\u0014\u001a\u00020\u0013J\b\u0010\u0015\u001a\u00020\u0013H\u0002J\u0006\u0010\u0016\u001a\u00020\u0013R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\u0017"}, d2 = {"Lcom/mlk/taskmanager/ui/steps/StepCounterViewModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/mlk/taskmanager/ui/steps/StepCounterUiState;", "connection", "Landroid/content/ServiceConnection;", "isBound", "", "stepCounterService", "Lcom/mlk/taskmanager/service/StepCounterService;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "onCleared", "", "resetCounter", "startStepCounter", "updateStepCount", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class StepCounterViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.mlk.taskmanager.ui.steps.StepCounterUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.mlk.taskmanager.ui.steps.StepCounterUiState> uiState = null;
    @org.jetbrains.annotations.Nullable()
    private com.mlk.taskmanager.service.StepCounterService stepCounterService;
    private boolean isBound = false;
    @org.jetbrains.annotations.NotNull()
    private final android.content.ServiceConnection connection = null;
    
    @javax.inject.Inject()
    public StepCounterViewModel(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.mlk.taskmanager.ui.steps.StepCounterUiState> getUiState() {
        return null;
    }
    
    private final void startStepCounter() {
    }
    
    public final void updateStepCount() {
    }
    
    public final void resetCounter() {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
}