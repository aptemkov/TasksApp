package io.github.aptemkov.tasksapp.data.usecase


import io.github.aptemkov.tasksapp.domain.models.Task
import io.github.aptemkov.tasksapp.domain.repository.LocalTasksRepository
import io.github.aptemkov.tasksapp.domain.usecase.GetTaskByIdUseCase
import javax.inject.Inject

class GetTaskByIdUseCaseImpl @Inject constructor(
    private val localRepository: LocalTasksRepository,
): GetTaskByIdUseCase {
    override suspend fun execute(id: String): Result<Task> {
        val task = localRepository.getTaskById(id)
        return Result.success(task)
    }
}
