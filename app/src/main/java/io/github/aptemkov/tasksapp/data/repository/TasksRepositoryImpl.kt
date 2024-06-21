package io.github.aptemkov.tasksapp.data.repository

import android.util.Log
import io.github.aptemkov.tasksapp.domain.models.Task
import io.github.aptemkov.tasksapp.domain.repository.TasksRepository
import io.github.aptemkov.tasksapp.presentation.home.testList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TasksRepositoryImpl @Inject constructor(): TasksRepository {
    private val _tasks: MutableList<Task> = testList.toMutableList()
    override val tasks: List<Task> get() = _tasks

    override fun getAllTasks(): List<Task> {
        return tasks
    }

    override fun getTaskById(id: String): Task? {
        val task = tasks.find { it.id == id }
        return task
    }

    override fun removeTaskById(id: String): Boolean {
        _tasks.removeAll { it.id == id }
        return true
    }

    override fun addTask(task: Task): Boolean {
        val prevTaskId = _tasks.indexOfFirst { it.id == task.id }

        if(prevTaskId != -1) {
            _tasks[prevTaskId] = task
        } else {
            _tasks.add(index = 0, element = task)
        }

        return true
    }

    override fun changeTaskDone(taskId: String, isDone: Boolean) {
        val index = _tasks.indexOfFirst { it.id == taskId }
        _tasks[index] = _tasks[index].copy(isDone = isDone)
    }

}