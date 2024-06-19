package io.github.aptemkov.tasksapp.presentation.home

import io.github.aptemkov.tasksapp.domain.models.Task

data class HomeScreenUiState(
    val tasksList: List<Task> = listOf(),
    val showCompletedTasks: Boolean = true,
) {
    val completedTasksNumber = tasksList.count { it.isDone }
}