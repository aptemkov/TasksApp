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

    val audioFile: String? = null,
    val audioDuration: Int = 0,
    val currentAudioPosition: Int = 0,
    val isAudioInputOpen: Boolean = false,
    val isAudioAdded: Boolean = false,
    val audioPlayerState: AudioPlayerState = AudioPlayerState.NONE
)

enum class AudioPlayerState {
    NONE,
    RECORDING,
    RECORDED,
    PLAYING,
    PAUSED,
}