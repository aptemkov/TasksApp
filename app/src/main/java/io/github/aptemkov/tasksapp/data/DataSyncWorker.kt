package io.github.aptemkov.tasksapp.data

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.aptemkov.tasksapp.domain.repository.TasksRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * DataSyncWorker необходим для запроса на синхронизацию данных раз в 8 часов
 *
 */
class DataSyncWorker @Inject constructor(
    @ApplicationContext context: Context,
    workerParams: WorkerParameters,
    private val repository: TasksRepository,
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        withContext(Dispatchers.IO + CoroutineExceptionHandler { coroutineContext, throwable ->  }) {
            repository.getAllTasks()
            repository.updateRemoteTasks()
        }
        return Result.success()
    }
}
