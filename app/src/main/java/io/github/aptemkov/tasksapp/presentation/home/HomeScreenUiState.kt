package io.github.aptemkov.tasksapp.presentation.home

import io.github.aptemkov.tasksapp.domain.models.Task

data class HomeScreenUiState(
    val tasksList: List<Task> = listOf(),
    val showCompletedTasks: Boolean = false,
) {
    val completedTasksNumber: Int = tasksList.count { it.isDone }
    val tasksListFiltered: List<Task> =
        if (showCompletedTasks) tasksList.filter { !it.isDone } else tasksList

}