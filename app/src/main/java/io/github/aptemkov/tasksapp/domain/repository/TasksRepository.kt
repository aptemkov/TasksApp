package io.github.aptemkov.tasksapp.domain.repository

import io.github.aptemkov.tasksapp.domain.models.Task
import kotlinx.coroutines.flow.Flow

interface TasksRepository {

    suspend fun getAllTasks(): Flow<List<Task>>

    suspend fun getTaskById(id: String): Task?

    suspend fun removeTaskById(id: String): Boolean

    suspend fun addTask(task: Task): Boolean

    suspend fun changeTaskDone(taskId: String, isDone: Boolean)

}