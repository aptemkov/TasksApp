package io.github.aptemkov.tasksapp.domain.usecase

import io.github.aptemkov.tasksapp.domain.models.Task
import kotlinx.coroutines.flow.Flow

interface AddTaskUseCase {
    suspend fun execute(task: Task): Result<Boolean>
}

interface ChangeTaskIsDoneUseCase {
    suspend fun execute(task: Task, isDone: Boolean): Result<Boolean>
}

interface EditTaskUseCase {
    suspend fun execute(task: Task): Result<Boolean>
}

interface GetAllTasksUseCase {
    fun execute(): Flow<List<Task>>
}

interface GetTaskByIdUseCase {
    suspend fun execute(id: String): Result<Task>
}

interface MergeTasksUseCase {
    suspend fun execute(): Result<Boolean>
}

interface RemoveTaskByIdUseCase {
    suspend fun execute(id: String): Result<Boolean>
}
