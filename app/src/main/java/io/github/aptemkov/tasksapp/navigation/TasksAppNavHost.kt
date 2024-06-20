package io.github.aptemkov.tasksapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import io.github.aptemkov.tasksapp.presentation.home.HomeScreen
import io.github.aptemkov.tasksapp.presentation.home.HomeScreenViewModel
import io.github.aptemkov.tasksapp.presentation.task.TaskScreen

@Composable
fun TasksAppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = HomeRoute,
    ) {
        composable<HomeRoute> {
            val viewModel: HomeScreenViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsState()
            HomeScreen(
                uiState = uiState,
                onItemClick = { id ->
                    navController.navigate(TaskRoute(id = id))
                },
                changeVisibility = { viewModel.changeVisibility() },
                onNewTaskClick = {
                    navController.navigate(TaskRoute(null))
                }
            )
        }

        composable<TaskRoute> {
            val viewModel: HomeScreenViewModel = hiltViewModel()
            val args = it.toRoute<TaskRoute>()
            TaskScreen(
                id = args.id,
                onNewTaskAdd = { newTask ->
                    viewModel.addTask(task = newTask)
                }
            )
        }
    }
}