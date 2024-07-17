package io.github.aptemkov.tasksapp.data

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.github.aptemkov.tasksapp.domain.repository.TasksRepository
import io.github.aptemkov.tasksapp.domain.usecase.MergeTasksUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * DataSyncWorker необходим для запроса на синхронизацию данных раз в 8 часов
 *
 */
@HiltWorker
class DataSyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val repository: TasksRepository,
    private val mergeTasksUseCase: MergeTasksUseCase,
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        withContext(Dispatchers.IO + CoroutineExceptionHandler { coroutineContext, throwable ->  }) {
            mergeTasksUseCase.execute()
        }
        return Result.success()
    }
}
