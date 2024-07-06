package io.github.aptemkov.tasksapp.app

import android.app.Application
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.HiltAndroidApp
import io.github.aptemkov.tasksapp.data.DataSyncWorker
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class TasksApp: Application() {
    override fun onCreate() {
        super.onCreate()
        setupWorkManager()
    }
}

fun setupWorkManager() {
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    val syncDataRequest = PeriodicWorkRequestBuilder<DataSyncWorker>(
        repeatInterval = 8,
        repeatIntervalTimeUnit = TimeUnit.HOURS
    )
        .setConstraints(constraints)
        .build()

    WorkManager.getInstance().enqueueUniquePeriodicWork(
        "DataSyncWork",
        ExistingPeriodicWorkPolicy.UPDATE,
        syncDataRequest
    )
}