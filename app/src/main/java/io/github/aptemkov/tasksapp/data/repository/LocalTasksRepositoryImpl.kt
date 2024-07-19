package io.github.aptemkov.tasksapp.data.repository

import io.github.aptemkov.tasksapp.data.database.TasksDatabase
import io.github.aptemkov.tasksapp.data.database.models.toTask
import io.github.aptemkov.tasksapp.data.database.models.toTaskDBO
import io.github.aptemkov.tasksapp.domain.models.Task
import io.github.aptemkov.tasksapp.domain.repository.LocalTasksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Данный класс необходим для взаимодействия с локальными данными
 */

@Singleton
class LocalTasksRepositoryImpl @Inject constructor(
    private val database: TasksDatabase
): LocalTasksRepository {
    override fun getAllTasks(): Flow<List<Task>> {
        return database.tasksDao.observeAll()
            .map { dbos ->
                dbos.map { it.toTask() }
            }
    }

    override suspend fun getTaskById(id: String): Task {
        return database.tasksDao.getById(id).toTask()
    }

    override suspend fun addTasks(tasks: List<Task>) {
        database.tasksDao.insert(tasks.map { it.toTaskDBO() })
    }

    override suspend fun removeTaskById(id: String) {
        database.tasksDao.removeById(id)
    }

    override suspend fun markTaskRemoved(id: String) {
//        database.tasksDao.markRemoved(id)
        database.tasksDao.removeById(id)
    }

    override suspend fun clearDataBase() {
        database.tasksDao.clean()
    }

    override suspend fun changeTaskDone(id: String, isDone: Boolean) {
        database.tasksDao.changeIsDone(id, isDone)
    }

    override suspend fun getAllLocalTasks(): List<Task> {
        return database.tasksDao.getAll().map { it.toTask() }
    }
}
