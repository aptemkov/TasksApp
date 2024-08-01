package io.github.aptemkov.tasksapp.presentation.task

import android.os.Handler
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.aptemkov.tasksapp.domain.audio.AudioPlayer
import io.github.aptemkov.tasksapp.domain.audio.AudioRecorder
import io.github.aptemkov.tasksapp.domain.models.Priority
import io.github.aptemkov.tasksapp.domain.models.Task
import io.github.aptemkov.tasksapp.domain.usecase.AddTaskUseCase
import io.github.aptemkov.tasksapp.domain.usecase.GetTaskByIdUseCase
import io.github.aptemkov.tasksapp.domain.usecase.RemoveTaskByIdUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class TaskScreenViewModel @Inject constructor(
    private val removeTaskByIdUseCase: RemoveTaskByIdUseCase,
    private val getTaskByIdUseCase: GetTaskByIdUseCase,
    private val addTaskUseCase: AddTaskUseCase,
    private val audioPlayer: AudioPlayer,
    private val audioRecorder: AudioRecorder,
): ViewModel() {

    private val _uiState = MutableStateFlow(TaskScreenUiState())
    val uiState = _uiState.asStateFlow()

    private val scope = viewModelScope + CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.e("TaskScreenVM", throwable.stackTrace.toString())
    }

    fun saveTask() {
        if(uiState.value.description.isBlank()) {
            _uiState.value = uiState.value.copy(
                isDescriptionError = true,
            )
        } else {
            addTask()
        }
    }

    private fun addTask() {
        val task = Task(
            id = uiState.value.id,
            description = uiState.value.description,
            priority = uiState.value.priority,
            deadline = uiState.value.deadLine,
            isDone = uiState.value.isDone,
            createDate = if (uiState.value.createdAt == 0L) System.currentTimeMillis() else uiState.value.createdAt,
            editDate = System.currentTimeMillis(),
        )
        scope.launch(Dispatchers.IO) {
            addTaskUseCase.execute(task)
        }
    }

    fun removeTask() {
        scope.launch(Dispatchers.IO) {
            removeTaskByIdUseCase.execute(uiState.value.id)
        }
    }

    fun loadTask(id: String) {
        scope.launch(Dispatchers.IO) {
            if(id == "0") {
                _uiState.value = uiState.value.copy(
                    id = UUID.randomUUID().toString()
                )
                return@launch
            }

            val task = getTaskByIdUseCase.execute(id).getOrNull()
            task?.let {
                _uiState.value = uiState.value.copy(
                    id = task.id,
                    description = task.description,
                    priority = task.priority,
                    isDone = task.isDone,
                    deadLine = task.deadline,
                    hasDeadLine = task.deadline > 0L,
                    createdAt = task.createDate,
                )
            }
        }
    }

    fun changeDescription(text: String) {
        _uiState.value = uiState.value.copy(
            description = text,
            isDescriptionError = false,
        )
    }

    fun changePriority(priority: Priority) {
        _uiState.value = uiState.value.copy(
            priority = priority
        )
    }

    fun changeDeadLine(deadLine: Long) {
        _uiState.value = uiState.value.copy(
            deadLine = deadLine
        )
    }

    fun changeHasDeadLine(hasDeadLine: Boolean) {
        _uiState.value = uiState.value.copy(
            hasDeadLine = hasDeadLine,
        )
        if(!hasDeadLine) {
            changeDeadLine(0L)
        }
    }

    fun changeIsAudioInputOpen() {
        _uiState.value = uiState.value.copy(
            isAudioInputOpen = uiState.value.isAudioInputOpen.not()
        )
    }

    fun changeIsAudioAdded(isAdded: Boolean) {
        _uiState.value = uiState.value.copy(
            isAudioAdded = isAdded
        )
    }

    fun changeAudioPlayerPosition(position: Float) {
        audioPlayer.changePosition(position.toInt())
    }

    fun onStartRecording() {
        val fileName = uiState.value.id
        audioRecorder.start(fileName)
        _uiState.value = uiState.value.copy(
            audioPlayerState = AudioPlayerState.RECORDING,
            audioFile = fileName
        )
    }

    fun onStopRecording() {
        audioRecorder.stop()
        audioPlayer.startPlayer(uiState.value.id)
        _uiState.value = uiState.value.copy(
            audioPlayerState = AudioPlayerState.RECORDED,
            isAudioAdded = true
        )
        getCurrentAudioPosition()
    }

    private fun getCurrentAudioPosition() {

        _uiState.value = uiState.value.copy(
            audioDuration = audioPlayer.getDuration(),
        )

        val handler = Handler()
        handler.postDelayed(
            object : Runnable {
                override fun run() {
                    try {
                        val currentPosition = audioPlayer.getCurrentPosition().toFloat()

                        _uiState.value = uiState.value.copy(
                            currentAudioPosition = currentPosition.toInt(),
                            audioDuration = audioPlayer.getDuration(),
                        )

                        handler.postDelayed(this, 500L)
                    } catch (e: Exception) {
                        _uiState.value = uiState.value.copy(
                            currentAudioPosition = 0,
                            audioDuration = audioPlayer.getDuration(),
                        )
                    }
                }
            },
            0
        )
    }

    fun onStartPlaying() {
        uiState.value.audioFile?.let {
            audioPlayer.playFile(it)
        }
        _uiState.value = uiState.value.copy(
            audioPlayerState = AudioPlayerState.PLAYING,
        )
    }

    fun onPausePlaying() {
        audioPlayer.pause()
        _uiState.value = uiState.value.copy(
            audioPlayerState = AudioPlayerState.PAUSED,
        )
    }

    fun onStopPlaying() {
        audioPlayer.stop()
        _uiState.value = uiState.value.copy(
            audioPlayerState = AudioPlayerState.NONE,
        )
    }

}