package com.mlk.taskmanager.ui.settings;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000R\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\u001a\u0016\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0003\u001a.\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0003\u001aL\u0010\u000b\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u000f2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u000fH\u0003\u001a.\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0003\u001a\b\u0010\u0013\u001a\u00020\u0001H\u0003\u001a.\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0003\u001a\u001a\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001aH\u0007\u001a=\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u001d2\u0011\u0010\u001e\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003\u00a2\u0006\u0002\b\u001fH\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b \u0010!\u001a\u0010\u0010\"\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\u0003\u001a<\u0010#\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010$\u001a\u00020%2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u00010\u000fH\u0003\u001a<\u0010\'\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010(\u001a\u00020%2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u00010\u000fH\u0003\u001a*\u0010)\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u000fH\u0003\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006+"}, d2 = {"BackupDialog", "", "onDismiss", "Lkotlin/Function0;", "ButtonPreference", "title", "", "description", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "onClick", "CategoryDialog", "categories", "", "onAddCategory", "Lkotlin/Function1;", "onRemoveCategory", "ListPreference", "value", "ProfileCard", "SettingsItem", "subtitle", "SettingsScreen", "navController", "Landroidx/navigation/NavController;", "viewModel", "Lcom/mlk/taskmanager/ui/settings/SettingsViewModel;", "SettingsSection", "iconTint", "Landroidx/compose/ui/graphics/Color;", "content", "Landroidx/compose/runtime/Composable;", "SettingsSection-9LQNqLg", "(Ljava/lang/String;Landroidx/compose/ui/graphics/vector/ImageVector;JLkotlin/jvm/functions/Function0;)V", "SettingsSectionHeader", "SettingsSwitch", "checked", "", "onCheckedChange", "SwitchPreference", "isChecked", "ThemePickerDialog", "onThemeSelected", "app_debug"})
public final class SettingsScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class, com.google.accompanist.permissions.ExperimentalPermissionsApi.class})
    @androidx.compose.runtime.Composable()
    public static final void SettingsScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.mlk.taskmanager.ui.settings.SettingsViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ProfileCard() {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SettingsItem(java.lang.String title, java.lang.String subtitle, androidx.compose.ui.graphics.vector.ImageVector icon, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SettingsSwitch(java.lang.String title, java.lang.String subtitle, androidx.compose.ui.graphics.vector.ImageVector icon, boolean checked, kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onCheckedChange) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ThemePickerDialog(kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onThemeSelected) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void BackupDialog(kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void CategoryDialog(kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, java.util.List<java.lang.String> categories, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onAddCategory, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onRemoveCategory) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SettingsSectionHeader(java.lang.String title) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SwitchPreference(java.lang.String title, java.lang.String description, androidx.compose.ui.graphics.vector.ImageVector icon, boolean isChecked, kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onCheckedChange) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ListPreference(java.lang.String title, java.lang.String value, androidx.compose.ui.graphics.vector.ImageVector icon, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ButtonPreference(java.lang.String title, java.lang.String description, androidx.compose.ui.graphics.vector.ImageVector icon, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
}