package io.github.aptemkov.tasksapp.presentation.task

import io.github.aptemkov.tasksapp.domain.models.Priority

data class TaskScreenUiState(
    val id: String = "0",
    val description: String = "",
    val priority: Priority = Priority.DEFAULT,
    val isDone: Boolean = false,
    val deadLine: Long = 0L,
    val createdAt: Long = 0L,
    val hasDeadLine: Boolean = false,
    val isDescriptionError: Boolean = false,
)