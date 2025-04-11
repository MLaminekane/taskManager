package com.mlk.taskmanager.ui.auth;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u001eH\u0002J\u0006\u0010 \u001a\u00020\u001eJ\u0006\u0010!\u001a\u00020\u001eJ\u0006\u0010\"\u001a\u00020\u001eJ\u000e\u0010#\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020\tJ\u000e\u0010%\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020\tJ\u000e\u0010&\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020\tR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R+\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR+\u0010\u0011\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0014\u0010\u0010\u001a\u0004\b\u0012\u0010\f\"\u0004\b\u0013\u0010\u000eR+\u0010\u0015\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0018\u0010\u0010\u001a\u0004\b\u0016\u0010\f\"\u0004\b\u0017\u0010\u000eR\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00070\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\'"}, d2 = {"Lcom/mlk/taskmanager/ui/auth/AuthViewModel;", "Landroidx/lifecycle/ViewModel;", "userRepository", "Lcom/mlk/taskmanager/data/repository/UserRepository;", "(Lcom/mlk/taskmanager/data/repository/UserRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/mlk/taskmanager/ui/auth/AuthUiState;", "<set-?>", "", "confirmPassword", "getConfirmPassword", "()Ljava/lang/String;", "setConfirmPassword", "(Ljava/lang/String;)V", "confirmPassword$delegate", "Landroidx/compose/runtime/MutableState;", "email", "getEmail", "setEmail", "email$delegate", "password", "getPassword", "setPassword", "password$delegate", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "checkLoginStatus", "", "clearInputs", "login", "logout", "register", "updateConfirmPassword", "value", "updateEmail", "updatePassword", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class AuthViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.mlk.taskmanager.data.repository.UserRepository userRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.mlk.taskmanager.ui.auth.AuthUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.mlk.taskmanager.ui.auth.AuthUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState email$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState password$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState confirmPassword$delegate = null;
    
    @javax.inject.Inject()
    public AuthViewModel(@org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.data.repository.UserRepository userRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.mlk.taskmanager.ui.auth.AuthUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEmail() {
        return null;
    }
    
    private final void setEmail(java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPassword() {
        return null;
    }
    
    private final void setPassword(java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getConfirmPassword() {
        return null;
    }
    
    private final void setConfirmPassword(java.lang.String p0) {
    }
    
    private final void checkLoginStatus() {
    }
    
    public final void updateEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void updatePassword(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void updateConfirmPassword(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void login() {
    }
    
    public final void register() {
    }
    
    public final void logout() {
    }
    
    private final void clearInputs() {
    }
}