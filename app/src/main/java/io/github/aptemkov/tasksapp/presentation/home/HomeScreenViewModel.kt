package io.github.aptemkov.tasksapp.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.aptemkov.tasksapp.domain.repository.TasksRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: TasksRepository,
): ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState = _uiState.asStateFlow()
    private val scope = viewModelScope + CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.e("HomeScreenVM", throwable.stackTrace.toString())
    }

    init {
        loadTasks()
    }

    private fun loadTasks() {
        scope.launch(Dispatchers.IO) {
            combine(uiState, repository.getAllTasks()) { homeState, tasks ->
                homeState.copy(
                    tasksList = tasks
                )
            }.stateIn(scope, SharingStarted.WhileSubscribed(), HomeScreenUiState()).collectLatest {
                _uiState.value = it
            }
        }
    }

    fun changeTaskIsDone(id: String, isDone: Boolean) {
        scope.launch(Dispatchers.IO) {
            repository.changeTaskDone(taskId = id, isDone = isDone)
        }
    }

    fun changeVisibility() {
        _uiState.value = uiState.value.copy(
            showCompletedTasks = !uiState.value.showCompletedTasks
        )
    }

}