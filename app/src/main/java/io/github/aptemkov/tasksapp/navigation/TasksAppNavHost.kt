package io.github.aptemkov.tasksapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import io.github.aptemkov.tasksapp.presentation.home.HomeScreen
import io.github.aptemkov.tasksapp.presentation.task.TaskScreen

@Composable
fun TasksAppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Home,
    ) {
        composable<Home> {
            HomeScreen(
                onClick = { description, id ->
                    navController.navigate(
                        Task(
                            description = description,
                            id = id,
                        )
                    )
                }
            )
        }

        composable<Task> {
            val args = it.toRoute<Task>()
            TaskScreen(
                description = args.description ?: "",
                id = args.id,
            )
        }
    }
}