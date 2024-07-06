package io.github.aptemkov.tasksapp.domain.repository

import io.github.aptemkov.tasksapp.domain.models.Task
import kotlinx.coroutines.flow.Flow

interface TasksRepository {

    suspend fun getAllTasks(): Flow<List<Task>>

    suspend fun updateRemoteTasks(): Result<Boolean>

    suspend fun getTaskById(id: String): Result<Task?>

    suspend fun removeTaskById(id: String): Result<Boolean>

    suspend fun addTask(task: Task): Result<Boolean>

    suspend fun changeTaskDone(task: Task, isDone: Boolean): Result<Boolean>

}