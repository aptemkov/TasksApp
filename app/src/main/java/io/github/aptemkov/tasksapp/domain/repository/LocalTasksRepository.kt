package io.github.aptemkov.tasksapp.domain.repository

import io.github.aptemkov.tasksapp.domain.models.Task
import kotlinx.coroutines.flow.Flow

interface LocalTasksRepository {
    fun getAllTasks(): Flow<List<Task>>

    suspend fun getTaskById(id: String): Task

    suspend fun addTasks(tasks: List<Task>)

    suspend fun removeTaskById(id: String)

    suspend fun markTaskRemoved(id: String)

    suspend fun clearDataBase()

    suspend fun changeTaskDone(id: String, isDone: Boolean)

    suspend fun getAllLocalTasks(): List<Task>
}
