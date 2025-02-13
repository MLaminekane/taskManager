package com.mlk.taskmanager.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mlk.taskmanager.ui.calendar.CalendarScreen
import com.mlk.taskmanager.ui.home.HomeScreen
import com.mlk.taskmanager.ui.settings.SettingsScreen
import com.mlk.taskmanager.ui.tasks.AddTaskScreen
import com.mlk.taskmanager.ui.tasks.TasksScreen
import com.mlk.taskmanager.ui.welcome.WelcomeScreen

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object Home : Screen("home")
    object Tasks : Screen("tasks")
    object Calendar : Screen("calendar")
    object Settings : Screen("settings")
    object AddTask : Screen("add_task")
    object AddRoutine : Screen("add_routine")
    object TaskDetail : Screen("task_detail/{taskId}") {
        fun createRoute(taskId: Long) = "task_detail/$taskId"
    }
    object RoutineDetail : Screen("routine_detail/{routineId}") {
        fun createRoute(routineId: Long) = "routine_detail/$routineId"
    }
}

@Composable
fun TaskManagerNavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Welcome.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Welcome.route) {
            WelcomeScreen(navController)
        }
        
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        
        composable(Screen.Tasks.route) {
            TasksScreen(navController)
        }
        
        composable(Screen.Calendar.route) {
            CalendarScreen(navController)
        }
        
        composable(Screen.Settings.route) {
            SettingsScreen(navController)
        }
        
        composable(Screen.AddTask.route) {
            AddTaskScreen(navController)
        }
        
        composable(Screen.AddRoutine.route) {
            // AddRoutineScreen(navController)
        }
        
        composable(Screen.TaskDetail.route) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toLongOrNull()
            if (taskId != null) {
                // TaskDetailScreen(taskId, navController)
            }
        }
        
        composable(Screen.RoutineDetail.route) { backStackEntry ->
            val routineId = backStackEntry.arguments?.getString("routineId")?.toLongOrNull()
            if (routineId != null) {
                // RoutineDetailScreen(routineId, navController)
            }
        }
    }
} 