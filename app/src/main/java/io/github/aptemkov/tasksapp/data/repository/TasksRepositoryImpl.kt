package io.github.aptemkov.tasksapp.data.repository

import io.github.aptemkov.tasksapp.domain.models.Task
import io.github.aptemkov.tasksapp.domain.repository.TasksRepository
import io.github.aptemkov.tasksapp.presentation.home.testList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TasksRepositoryImpl @Inject constructor(): TasksRepository {
    val tasks: List<Task> = testList

    private val _tasksFlow = MutableStateFlow(tasks)

    override suspend fun getAllTasks(): Flow<List<Task>> {
        return _tasksFlow.asStateFlow()
    }

    override suspend fun getTaskById(id: String): Task? {
        val task = _tasksFlow.value.find { it.id == id }
        return task
    }

    override suspend fun removeTaskById(id: String): Boolean {
        _tasksFlow.update { prev ->
            prev.filter { it.id != id }
        }
        return true
    }

    override suspend fun addTask(task: Task): Boolean {
        val prevTaskPosition = _tasksFlow.value.indexOfFirst { it.id == task.id }

        if(prevTaskPosition != -1) {
            _tasksFlow.update { prev ->
                prev.toMutableList().apply {
                    this[prevTaskPosition] = task
                }
            }
        } else {
            _tasksFlow.update { prev ->
                prev.toMutableList().apply {
                    this.add(0, element = task)
                }
            }
        }
        return true
    }

    override suspend fun changeTaskDone(taskId: String, isDone: Boolean) {
        val index = _tasksFlow.value.indexOfFirst { it.id == taskId }
        if(index == -1) return
        _tasksFlow.update { prev ->
            prev.toMutableList().apply {
                this[index] = this[index].copy(isDone = isDone)
            }
        }
    }

}