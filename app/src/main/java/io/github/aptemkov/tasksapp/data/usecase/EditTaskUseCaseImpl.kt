package io.github.aptemkov.tasksapp.data.usecase


import io.github.aptemkov.tasksapp.domain.models.Task
import io.github.aptemkov.tasksapp.domain.repository.LocalTasksRepository
import io.github.aptemkov.tasksapp.domain.repository.RemoteTasksRepository
import io.github.aptemkov.tasksapp.domain.usecase.EditTaskUseCase
import javax.inject.Inject

class EditTaskUseCaseImpl @Inject constructor(
    private val localRepository: LocalTasksRepository,
    private val remoteRepository: RemoteTasksRepository
): EditTaskUseCase {
    override suspend fun execute(task: Task): Result<Boolean> {
        localRepository.addTasks(listOf(task))
        return remoteRepository.editTaskRemote(task)
    }
}
