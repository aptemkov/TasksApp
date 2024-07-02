package io.github.aptemkov.tasksapp.presentation.task

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.aptemkov.tasksapp.domain.models.Priority
import io.github.aptemkov.tasksapp.domain.models.Task
import io.github.aptemkov.tasksapp.domain.repository.TasksRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class TaskScreenViewModel @Inject constructor(
    private val repository: TasksRepository,
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
        val id = uiState.value.id
        val task = Task(
            id = if (id != "0") id else "${Random.nextInt()}",
            description = uiState.value.description,
            priority = uiState.value.priority,
            deadline = uiState.value.deadLine,
            isDone = uiState.value.isDone,
            createDate = if (uiState.value.createdAt == 0L) System.currentTimeMillis() else uiState.value.createdAt,
            editDate = System.currentTimeMillis(),
        )
        scope.launch(Dispatchers.IO) {
            repository.addTask(task)
        }
    }

    fun removeTask() {
        scope.launch(Dispatchers.IO) {
            repository.removeTaskById(uiState.value.id)
        }
    }

    fun loadTask(id: String) {
        scope.launch(Dispatchers.IO) {
            val task = repository.getTaskById(id)
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

}