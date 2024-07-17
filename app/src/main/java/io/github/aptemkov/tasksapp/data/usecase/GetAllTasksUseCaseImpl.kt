package io.github.aptemkov.tasksapp.data.usecase

import io.github.aptemkov.tasksapp.domain.models.Task
import io.github.aptemkov.tasksapp.domain.repository.LocalTasksRepository
import io.github.aptemkov.tasksapp.domain.usecase.GetAllTasksUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTasksUseCaseImpl @Inject constructor(
    private val localRepository: LocalTasksRepository,
): GetAllTasksUseCase {
    override fun execute(): Flow<List<Task>> {
        return localRepository.getAllTasks()
    }
}
