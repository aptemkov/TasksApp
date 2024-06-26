package io.github.aptemkov.tasksapp.presentation.task

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.aptemkov.tasksapp.domain.models.Priority
import io.github.aptemkov.tasksapp.domain.models.Task
import io.github.aptemkov.tasksapp.domain.repository.TasksRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class TaskScreenViewModel @Inject constructor(
    private val repository: TasksRepository,
): ViewModel() {

    private val _uiState = MutableStateFlow(TaskScreenUiState())
    val uiState = _uiState.asStateFlow()

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
        repository.addTask(task)
    }

    fun removeTask() {
        repository.removeTaskById(uiState.value.id)
    }

    fun loadTask(id: String, isEdit: Boolean) {
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
                isEditState = isEdit,
            )
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