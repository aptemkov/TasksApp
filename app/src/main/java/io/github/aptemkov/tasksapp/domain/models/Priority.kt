package io.github.aptemkov.tasksapp.domain.models

import kotlinx.serialization.Serializable

@Serializable
enum class Priority {
    LOW,
    MEDIUM,
    HIGH,
}