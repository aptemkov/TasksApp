package io.github.aptemkov.tasksapp.data.usecase


import io.github.aptemkov.tasksapp.domain.models.Task
import io.github.aptemkov.tasksapp.domain.repository.LocalTasksRepository
import io.github.aptemkov.tasksapp.domain.repository.RemoteTasksRepository
import io.github.aptemkov.tasksapp.domain.usecase.AddTaskUseCase
import javax.inject.Inject

class AddTaskUseCaseImpl @Inject constructor(
    private val localRepository: LocalTasksRepository,
    private val remoteRepository: RemoteTasksRepository
): AddTaskUseCase {

    override suspend fun execute(task: Task): Result<Boolean> {
        localRepository.addTasks(listOf(task))
        return remoteRepository.addTaskRemote(task)
    }
}
