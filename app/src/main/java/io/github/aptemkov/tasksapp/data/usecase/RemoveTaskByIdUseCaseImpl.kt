package io.github.aptemkov.tasksapp.data.usecase


import io.github.aptemkov.tasksapp.domain.repository.LocalTasksRepository
import io.github.aptemkov.tasksapp.domain.repository.RemoteTasksRepository
import io.github.aptemkov.tasksapp.domain.usecase.RemoveTaskByIdUseCase
import javax.inject.Inject

class RemoveTaskByIdUseCaseImpl @Inject constructor(
    private val localRepository: LocalTasksRepository,
    private val remoteRepository: RemoteTasksRepository
): RemoveTaskByIdUseCase {
    override suspend fun execute(id: String): Result<Boolean> {
        localRepository.markTaskRemoved(id)
        return remoteRepository.deleteTaskRemote(id)
    }
}
