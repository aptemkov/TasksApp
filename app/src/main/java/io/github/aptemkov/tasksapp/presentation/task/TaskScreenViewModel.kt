package io.github.aptemkov.tasksapp.presentation.task

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.aptemkov.tasksapp.domain.models.Task
import io.github.aptemkov.tasksapp.domain.repository.TasksRepository
import javax.inject.Inject

@HiltViewModel
class TaskScreenViewModel @Inject constructor(
    private val repository: TasksRepository,
): ViewModel() {

    fun addTask(task: Task) {
        repository.addTask(task)
    }
}