package io.github.aptemkov.tasksapp.domain.repository

import io.github.aptemkov.tasksapp.domain.models.Task

interface TasksRepository {
    val tasks: List<Task>

    fun getAllTasks(): List<Task>

    fun getTaskById(id: String): Task?

    fun removeTaskById(id: String): Boolean

    fun addTask(task: Task): Boolean

    fun changeTaskDone(taskId: String, isDone: Boolean)

}