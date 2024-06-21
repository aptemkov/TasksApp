package io.github.aptemkov.tasksapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import io.github.aptemkov.tasksapp.presentation.home.HomeScreen
import io.github.aptemkov.tasksapp.presentation.home.HomeScreenViewModel
import io.github.aptemkov.tasksapp.presentation.task.TaskScreen
import io.github.aptemkov.tasksapp.presentation.task.TaskScreenViewModel
import io.github.aptemkov.tasksapp.presentation.task.getTaskScreenArgument

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
                    navController.navigate(TaskRoute(id = id, isEdit = true))
                },
                onDetailsClick = { id ->
                    navController.navigate(TaskRoute(id = id, isEdit = false))
                },
                onNewTaskClick = {
                    navController.navigate(TaskRoute(id = null, isEdit = false))
                },
                changeVisibility = { viewModel.changeVisibility() },
            )
        }

        composable<TaskRoute> {
            val viewModel: TaskScreenViewModel = hiltViewModel()
            val args = it.toRoute<TaskRoute>()
            val isEdit = args.isEdit
            val id = args.id

            val argument = getTaskScreenArgument(id = id, isEdit = isEdit)

            TaskScreen(
                tasksScreenArgument = argument,
                onNewTaskAdd = { newTask ->
                    viewModel.addTask(task = newTask)
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}