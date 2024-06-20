package io.github.aptemkov.tasksapp.domain.models

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.github.aptemkov.tasksapp.R
import kotlinx.serialization.Serializable

@Serializable
enum class Priority {
    LOW,
    DEFAULT,
    HIGH,
}

val Priority.textName: String
    @Composable
    get() = when (this) {
        Priority.LOW -> stringResource(id = R.string.priority_low)
        Priority.DEFAULT -> stringResource(id = R.string.priority_default)
        Priority.HIGH -> stringResource(id = R.string.priority_high)
    }