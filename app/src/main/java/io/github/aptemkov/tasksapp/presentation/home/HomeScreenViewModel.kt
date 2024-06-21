package io.github.aptemkov.tasksapp.presentation.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.aptemkov.tasksapp.domain.repository.TasksRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
    }

    fun changeTaskIsDone(id: String, isDone: Boolean) {
        repository.changeTaskDone(taskId = id, isDone = isDone)
        loadTasks()
    }

    fun changeVisibility() {
        _uiState.value = uiState.value.copy(
            showCompletedTasks = !uiState.value.showCompletedTasks
        )
    }

}