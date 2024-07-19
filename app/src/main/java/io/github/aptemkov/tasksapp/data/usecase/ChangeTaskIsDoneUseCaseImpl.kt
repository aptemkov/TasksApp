package io.github.aptemkov.tasksapp.data.usecase

import io.github.aptemkov.tasksapp.domain.models.Task
import io.github.aptemkov.tasksapp.domain.repository.LocalTasksRepository
import io.github.aptemkov.tasksapp.domain.repository.RemoteTasksRepository
import io.github.aptemkov.tasksapp.domain.usecase.ChangeTaskIsDoneUseCase
import javax.inject.Inject

class ChangeTaskIsDoneUseCaseImpl @Inject constructor(
    private val localRepository: LocalTasksRepository,
    private val remoteRepository: RemoteTasksRepository
): ChangeTaskIsDoneUseCase {
    override suspend fun execute(task: Task, isDone: Boolean): Result<Boolean> {
        localRepository.changeTaskDone(task.id, isDone)
        return remoteRepository.editTaskRemote(task.copy(isDone = isDone))
    }
}
