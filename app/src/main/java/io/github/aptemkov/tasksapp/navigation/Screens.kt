package io.github.aptemkov.tasksapp.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
data class Task(
    val description: String?,
    val id: String,
)