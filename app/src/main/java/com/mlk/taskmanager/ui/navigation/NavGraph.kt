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
import com.mlk.taskmanager.ui.tasks.TaskDetailScreen
import com.mlk.taskmanager.ui.routines.AddRoutineScreen
import com.mlk.taskmanager.ui.routines.RoutineDetailScreen
import com.mlk.taskmanager.ui.routines.RoutinesScreen
import com.mlk.taskmanager.ui.project.ProjectDetailScreen
import com.mlk.taskmanager.ui.pomodoro.PomodoroScreen
import com.mlk.taskmanager.ui.steps.StepCounterScreen
import com.mlk.taskmanager.ui.auth.LoginScreen
import com.mlk.taskmanager.ui.auth.RegisterScreen

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object Home : Screen("home")
    object Tasks : Screen("tasks")
    object Calendar : Screen("calendar")
    object Settings : Screen("settings")
    object AddTask : Screen("add_task")
    object AddRoutine : Screen("add_routine")
    object Routines : Screen("routines")
    object TaskDetail : Screen("task_detail/{taskId}") {
        fun createRoute(taskId: Long) = "task_detail/$taskId"
    }
    object RoutineDetail : Screen("routine_detail/{routineId}") {
        fun createRoute(routineId: Long) = "routine_detail/$routineId"
    }
    object ProjectDetail : Screen("project_detail/{projectId}") {
        fun createRoute(projectId: Long) = "project_detail/$projectId"
    }
    object Pomodoro : Screen("pomodoro")
    object StepCounter : Screen("step_counter")
    object Login : Screen("login")
    object Register : Screen("register")
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
            AddRoutineScreen(navController)
        }

        composable(Screen.Routines.route) {
            RoutinesScreen(navController)
        }

        composable(Screen.TaskDetail.route) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toLongOrNull()
            if (taskId != null) {
                TaskDetailScreen(taskId, navController)
            }
        }

        composable(Screen.RoutineDetail.route) { backStackEntry ->
            val routineId = backStackEntry.arguments?.getString("routineId")?.toLongOrNull()
            if (routineId != null) {
                RoutineDetailScreen(routineId, navController)
            }
        }

        composable(Screen.ProjectDetail.route) { backStackEntry ->
            val projectId = backStackEntry.arguments?.getString("projectId")?.toLongOrNull()
            if (projectId != null) {
                ProjectDetailScreen(
                    projectId = projectId,
                    onNavigateToTask = { taskId ->
                        navController.navigate(Screen.TaskDetail.createRoute(taskId))
                    }
                )
            }
        }

        composable(Screen.Pomodoro.route) {
            PomodoroScreen(navController = navController)
        }

        composable(Screen.StepCounter.route) {
            StepCounterScreen(navController = navController)
        }

        composable(Screen.Login.route) {
            LoginScreen(
                onNavigateToRegister = { navController.navigate(Screen.Register.route) },
                onLoginSuccess = { navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Login.route) { inclusive = true }
                }}
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(
                onNavigateToLogin = { navController.navigate(Screen.Login.route) },
                onRegisterSuccess = { navController.navigate(Screen.Login.route) }
            )
        }
    }
}