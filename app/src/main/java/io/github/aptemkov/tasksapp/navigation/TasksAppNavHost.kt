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
                onChangeTaskIsDone = { id: String, isDone: Boolean ->
                    viewModel.changeTaskIsDone(id = id, isDone = isDone)
                },
                changeVisibility = { viewModel.changeVisibility() },
            )
        }

        composable<TaskRoute> {
            val viewModel: TaskScreenViewModel = hiltViewModel()
            val args = it.toRoute<TaskRoute>()
            val argument = getTaskScreenArgument(id = args.id, isEdit = args.isEdit)
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            TaskScreen(
                uiState = uiState,
                tasksScreenArgument = argument,
                onNewTaskAdd = {
                    viewModel.saveTask()
                },
                onRemoveTask = {
                    viewModel.removeTask()
                },
                onLoadTask = { taskId ->
                    viewModel.loadTask(id = taskId)
                },
                onDescriptionChange = { description ->
                    viewModel.changeDescription(description)
                },
                onPriorityChange = { priority ->
                    viewModel.changePriority(priority)
                },
                onDeadLineChange = { deadLine ->
                    viewModel.changeDeadLine(deadLine)
                },
                onHasDeadLineChange = { hasDeadLine ->
                    viewModel.changeHasDeadLine(hasDeadLine)
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}