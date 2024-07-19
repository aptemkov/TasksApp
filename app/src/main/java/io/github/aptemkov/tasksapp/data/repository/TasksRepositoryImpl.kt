package io.github.aptemkov.tasksapp.data.repository

import android.util.Log
import io.github.aptemkov.tasksapp.R
import io.github.aptemkov.tasksapp.app.providers.ResourceProvider
import io.github.aptemkov.tasksapp.data.database.TasksDatabase
import io.github.aptemkov.tasksapp.data.database.models.toTask
import io.github.aptemkov.tasksapp.data.database.models.toTaskDBO
import io.github.aptemkov.tasksapp.data.remote.TasksApi
import io.github.aptemkov.tasksapp.data.remote.models.AllElementsRequest
import io.github.aptemkov.tasksapp.data.remote.models.Element
import io.github.aptemkov.tasksapp.data.remote.models.ElementRequest
import io.github.aptemkov.tasksapp.data.remote.toElement
import io.github.aptemkov.tasksapp.data.remote.toTask
import io.github.aptemkov.tasksapp.domain.models.Task
import io.github.aptemkov.tasksapp.domain.repository.TasksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Данный класс необходим для взаимодействия с данными
 * Уже не используется в приложении, но пока что я его оставлю для некоторых тестов новой логики
 */

@Singleton
class TasksRepositoryImpl @Inject constructor(
    private val database: TasksDatabase,
    private val api: TasksApi,
    private val resourceProvider: ResourceProvider,
) : TasksRepository {

    override suspend fun getAllTasks(): Flow<List<Task>> {
        val remoteResult = getAllTasksFromRemote()
        if (remoteResult.isSuccess) {
            val remoteList = remoteResult.getOrDefault(listOf()).map { it.toTask().toTaskDBO() }
            database.tasksDao.insert(remoteList)
        }

        return database.tasksDao.observeAll()
            .map { dbos ->
                dbos.map { it.toTask() }
            }
    }

    override suspend fun updateRemoteTasks(): Result<Boolean> {
        val localList = database.tasksDao.getAll().map { it.toTask().toElement() }
        return updateTasksRemote(localList)
    }

    override suspend fun getTaskById(id: String): Result<Task> {
        val task = database.tasksDao.getById(id).toTask()
        return Result.success(task)
    }

    override suspend fun removeTaskById(id: String): Result<Boolean> {
        database.tasksDao.removeById(id)
        return deleteTaskRemote(id)
    }

    override suspend fun addTask(task: Task): Result<Boolean> {
        database.tasksDao.insert(listOf(task.toTaskDBO()))
        return addTaskRemote(task)
    }

    override suspend fun changeTaskDone(task: Task, isDone: Boolean): Result<Boolean> {
        database.tasksDao.changeIsDone(task.id, isDone)
        return editTaskRemote(task.copy(isDone = isDone))
    }

    private suspend fun getAllTasksFromRemote(): Result<List<Element>> {
        return try {
            val response = api.getAllElements()
            if (response.status == "ok") {
                revision = response.revision
            }
            Result.success(response.list)
        } catch (e: Exception) {
            Log.e(ERROR_LOG_TAG, "Get all: ${e.localizedMessage}")
            Result.failure(
                Exception(
                    resourceProvider.getString(R.string.error_receiving_data_from_server, e.localizedMessage ?: "")
                )
            )
        }
    }

    private suspend fun getTaskByIdRemote(id: String): Result<Task> {
        return try {
            val response = api.getTaskById(id)
            if (response.status == "ok") {
                revision = response.revision
            }
            Result.success(response.element.toTask())
        } catch (e: Exception) {
            Log.e(ERROR_LOG_TAG, "Get by id: ${e.localizedMessage}")
            Result.failure(e)
        }
    }

    private suspend fun addTaskRemote(task: Task): Result<Boolean> {
        return try {
            val response =
                api.addElement(
                    revision = revision,
                    element = ElementRequest(task.toElement())
                )
            if (response.status == "ok") {
                revision = response.revision
            }
            Result.success(true)
        } catch (e: Exception) {
            val editResult = editTaskRemote(task)
            if (editResult.isSuccess) {
                Result.success(true)
            } else {
                Log.e(ERROR_LOG_TAG, "Add task: ${e.localizedMessage}")
                Result.failure(
                    Exception(
                        resourceProvider.getString(R.string.error_adding_new_task, e.localizedMessage ?: "")
                    )
                )
            }
        }
    }

    private suspend fun editTaskRemote(task: Task): Result<Boolean> {
        return try {
            val response = api.updateElement(
                revision = revision,
                id = task.id,
                element = ElementRequest(task.toElement())
            )

            if (response.status == "ok") {
                revision = response.revision
            }
            Result.success(true)
        } catch (e: Exception) {
            Log.e(ERROR_LOG_TAG, "Edit task: ${e.localizedMessage}")
            Result.success(true)
        }
    }

    private suspend fun deleteTaskRemote(id: String): Result<Boolean> {
        return try {
            val response = api.deleteElement(revision = revision, id = id)
            if (response.status == "ok") {
                revision = response.revision
            }
            Result.success(true)
        } catch (e: Exception) {
            Log.e(ERROR_LOG_TAG, "Delete task: ${e.localizedMessage}")
            Result.failure(
                Exception(
                    resourceProvider.getString(R.string.error_deleting_task, e.localizedMessage ?: "")
                )
            )
        }
    }

    private suspend fun updateTasksRemote(localList: List<Element>): Result<Boolean> {
        return try {
            val response = api.updateElements(
                revision = revision,
                elements = AllElementsRequest(localList),
            )
            if(response.status == "ok") {
                revision = response.revision
            }
            Result.success(true)
        } catch (e: Exception) {
            Log.e(ERROR_LOG_TAG, "Update tasks: ${e.localizedMessage}")
            Result.failure(
                Exception(
                    resourceProvider.getString(R.string.error_editing_task, e.localizedMessage ?: "")
                )
            )
        }
    }

    companion object {
        private var ERROR_LOG_TAG = "RepositoryError"
        private var revision = 0
    }

}