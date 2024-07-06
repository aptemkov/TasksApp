package io.github.aptemkov.tasksapp.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.aptemkov.tasksapp.R
import io.github.aptemkov.tasksapp.app.receivers.NetworkBroadcastReceiver
import io.github.aptemkov.tasksapp.app.providers.ResourceProvider
import io.github.aptemkov.tasksapp.domain.models.Task
import io.github.aptemkov.tasksapp.domain.repository.TasksRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
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
    private val connectivityReceiver: NetworkBroadcastReceiver,
    private val resourceProvider: ResourceProvider,
): ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiAlert = MutableStateFlow("")
    val uiAlert: StateFlow<String> = _uiAlert

    private val scope = viewModelScope + CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.e("HomeScreenVM", throwable.stackTrace.toString())
    }

    init {
        loadTasks()
        observeNetworkChanges()
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

    fun changeTaskIsDone(task: Task, isDone: Boolean) {
        scope.launch(Dispatchers.IO) {
            val result = repository.changeTaskDone(task = task, isDone = isDone)
            if(result.isFailure) {
                _uiAlert.value = result.exceptionOrNull()?.message ?:
                    resourceProvider.getString(R.string.unexpected_exception)
            }
        }
    }

    fun changeVisibility() {
        _uiState.value = uiState.value.copy(
            showCompletedTasks = !uiState.value.showCompletedTasks
        )
    }

    private fun observeNetworkChanges() {
        scope.launch(Dispatchers.IO) {
            connectivityReceiver.connection.collect { isConnected ->
                if (isConnected) {
                    repository.updateRemoteTasks()
                    repository.getAllTasks()
                } else {
                    _uiAlert.value = resourceProvider.getString(R.string.disconnected_from_internet)
                }
            }
        }
    }



}