package io.github.aptemkov.tasksapp.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.aptemkov.tasksapp.domain.models.Task
import io.github.aptemkov.tasksapp.domain.repository.TasksRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: TasksRepository,
): ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadTasks()
    }

    private fun loadTasks() {
        val tasks = repository.getAllTasks()
        _uiState.value = uiState.value.copy(
            tasksList = tasks
        )
        Log.i("TestTest", "loadTasks: ${uiState.value.tasksList.size} + ${uiState.value.tasksList.first().id}")
    }

    fun addTask(task: Task) {
        viewModelScope.launch {
            repository.addTask(task)
            delay(500)
            loadTasks()
        }
    }

    fun changeTaskIsDone(id: String, isDone: Boolean) {
        repository.changeTaskDone(taskId = id, isDone = isDone)
    }

    fun changeVisibility() {
        _uiState.value = uiState.value.copy(
            showCompletedTasks = !uiState.value.showCompletedTasks
        )
    }

}