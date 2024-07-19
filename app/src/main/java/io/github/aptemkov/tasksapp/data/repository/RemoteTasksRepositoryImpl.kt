package io.github.aptemkov.tasksapp.data.repository

import android.util.Log
import io.github.aptemkov.tasksapp.R
import io.github.aptemkov.tasksapp.app.providers.ResourceProvider
import io.github.aptemkov.tasksapp.data.remote.TasksApi
import io.github.aptemkov.tasksapp.data.remote.models.AllElementsRequest
import io.github.aptemkov.tasksapp.data.remote.models.Element
import io.github.aptemkov.tasksapp.data.remote.models.ElementRequest
import io.github.aptemkov.tasksapp.data.remote.toElement
import io.github.aptemkov.tasksapp.data.remote.toTask
import io.github.aptemkov.tasksapp.domain.models.Task
import io.github.aptemkov.tasksapp.domain.repository.RemoteTasksRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Данный класс необходим для взаимодействия с данными с сервера
 */

@Singleton
class RemoteTasksRepositoryImpl @Inject constructor(
    private val api: TasksApi,
    private val resourceProvider: ResourceProvider
) : RemoteTasksRepository {

    override suspend fun getAllTasksFromRemote(): Result<List<Element>> {
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
                    resourceProvider.getString(
                        R.string.error_receiving_data_from_server,
                        e.localizedMessage ?: ""
                    )
                )
            )
        }
    }

    override suspend fun getTaskByIdRemote(id: String): Result<Task> {
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

    override suspend fun addTaskRemote(task: Task): Result<Boolean> {
        getRevision()
        return try {
            val response =
                api.addElement(revision = revision, element = ElementRequest(task.toElement()))
            if (response.status == "ok") {
                revision = response.revision
            }
            Result.success(true)
        } catch (e: Exception) {
            Log.e(ERROR_LOG_TAG, "Add task: ${e.localizedMessage}")
            Result.failure(
                Exception(
                    resourceProvider.getString(
                        R.string.error_adding_new_task,
                        e.localizedMessage ?: ""
                    )
                )
            )
        }
    }

    override suspend fun editTaskRemote(task: Task): Result<Boolean> {
        getRevision()
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

    override suspend fun deleteTaskRemote(id: String): Result<Boolean> {
        getRevision()
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
                    resourceProvider.getString(
                        R.string.error_deleting_task,
                        e.localizedMessage ?: ""
                    )
                )
            )
        }
    }

    override suspend fun updateTasksRemote(localList: List<Task>): Result<Boolean> {
        getRevision()
        return try {
            val response =
                api.updateElements(
                    revision = revision,
                    elements = AllElementsRequest(localList.map { it.toElement() })
                )
            if (response.status == "ok") {
                revision = response.revision
            }
            Result.success(true)
        } catch (e: Exception) {
            Log.e(ERROR_LOG_TAG, "Update tasks: ${e.localizedMessage}")
            Result.failure(
                Exception(
                    resourceProvider.getString(
                        R.string.error_editing_task,
                        e.localizedMessage ?: ""
                    )
                )
            )
        }
    }

    override suspend fun getRevision(): Result<Int> {
        getAllTasksFromRemote()
        return Result.success(revision)
    }

    companion object {
        private var ERROR_LOG_TAG = "RepositoryError"
        private var revision = 0
    }
}
