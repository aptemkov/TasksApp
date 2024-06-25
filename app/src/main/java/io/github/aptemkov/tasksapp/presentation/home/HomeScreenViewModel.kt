package io.github.aptemkov.tasksapp.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.aptemkov.tasksapp.domain.repository.TasksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
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
        viewModelScope.launch(Dispatchers.IO) {
            combine(uiState, repository.getAllTasks()) { homeState, tasks ->
                homeState.copy(
                    tasksList = tasks
                )
            }.collectLatest {
                Log.i("testtest", "loadTasks: collected")
                _uiState.value = it
            }
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