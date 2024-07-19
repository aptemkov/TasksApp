package io.github.aptemkov.tasksapp.domain.repository

import io.github.aptemkov.tasksapp.data.remote.models.Element
import io.github.aptemkov.tasksapp.domain.models.Task

interface RemoteTasksRepository {

    suspend fun getAllTasksFromRemote(): Result<List<Element>>

    suspend fun getTaskByIdRemote(id: String): Result<Task>

    suspend fun addTaskRemote(task: Task): Result<Boolean>

    suspend fun editTaskRemote(task: Task): Result<Boolean>

    suspend fun deleteTaskRemote(id: String): Result<Boolean>

    suspend fun updateTasksRemote(localList: List<Task>): Result<Boolean>

    suspend fun getRevision(): Result<Int>

}