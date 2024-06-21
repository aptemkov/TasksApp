package io.github.aptemkov.tasksapp.navigation

import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

@Serializable
data class TaskRoute(
    val id: String?,
    val isEdit: Boolean = false,
)