package io.github.aptemkov.tasksapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import io.github.aptemkov.tasksapp.presentation.details.DetailScreen
import io.github.aptemkov.tasksapp.presentation.home.HomeScreen
import io.github.aptemkov.tasksapp.presentation.home.HomeScreenViewModel
import io.github.aptemkov.tasksapp.presentation.settings.SettingsScreen
import io.github.aptemkov.tasksapp.presentation.settings.SettingsViewModel
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
            HomeRouteDestination(navController)
        }

        composable<TaskRoute> {
            TaskRouteDestination(it, navController)
        }

        composable<SettingsRoute> {
            SettingsRouteDestination(navController)
        }

        composable<DetailsRoute> {
            DetailsRouteDestination(navController)
        }
    }
}

@Composable
private fun TaskRouteDestination(
    it: NavBackStackEntry,
    navController: NavHostController
) {
    val viewModel: TaskScreenViewModel = hiltViewModel()
    val args = it.toRoute<TaskRoute>()
    val argument = getTaskScreenArgument(id = args.id, isEdit = args.isEdit)
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    TaskScreen(
        uiState = uiState,
        tasksScreenArgument = argument,
        onNewTaskAdd = viewModel::saveTask,
        onRemoveTask = viewModel::removeTask,
        onLoadTask = viewModel::loadTask,
        onDescriptionChange = viewModel::changeDescription,
        onPriorityChange = viewModel::changePriority,
        onDeadLineChange = viewModel::changeDeadLine,
        onHasDeadLineChange = viewModel::changeHasDeadLine,
        onBack = navController::navigateUp,
        onChangeIsAudioInputOpen = viewModel::changeIsAudioInputOpen,
        onChangeIsAudioAdded = viewModel::changeIsAudioAdded,
        onChangeAudioPlayerPosition = viewModel::changeAudioPlayerPosition,
        onStartRecording = viewModel::onStartRecording,
        onStopRecording = viewModel::onStopRecording,
        onStartPlaying = viewModel::onStartPlaying,
        onPausePlaying = viewModel::onPausePlaying,
        onStopPlaying = viewModel::onStopPlaying,
    )
}

@Composable
private fun HomeRouteDestination(navController: NavHostController) {
    val viewModel: HomeScreenViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val uiAlertState by viewModel.uiAlert.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState,
        uiAlertState = uiAlertState,
        onItemClick = { id ->
            navController.navigate(TaskRoute(id = id, isEdit = true))
        },
        onDetailsClick = { id ->
            navController.navigate(TaskRoute(id = id, isEdit = false))
        },
        onNewTaskClick = {
            navController.navigate(TaskRoute(id = null, isEdit = false))
        },
        onChangeTaskIsDone = viewModel::changeTaskIsDone,
        changeVisibility = viewModel::changeVisibility,
        onSettingsClick = {
            navController.navigate(SettingsRoute)
        }
    )
}

@Composable
private fun SettingsRouteDestination(navController: NavHostController) {
    val viewModel: SettingsViewModel = hiltViewModel()
    val selectedTheme by viewModel.selectedTheme.collectAsStateWithLifecycle()

    SettingsScreen(
        selectedTheme = selectedTheme,
        onThemeChange = viewModel::selectTheme,
        onBack = { navController.navigateUp() },
        onDetails = { navController.navigate(DetailsRoute) }
    )
}

@Composable
private fun DetailsRouteDestination(navController: NavHostController) {

    DetailScreen(
        onClose = {
            navController.navigateUp()
        }
    )
}