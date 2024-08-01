package io.github.aptemkov.tasksapp.app.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.aptemkov.tasksapp.app.providers.ResourceProvider
import io.github.aptemkov.tasksapp.data.audio.AudioPlayerImpl
import io.github.aptemkov.tasksapp.data.audio.AudioRecorderImpl
import io.github.aptemkov.tasksapp.data.database.TasksDatabase
import io.github.aptemkov.tasksapp.data.remote.TasksApi
import io.github.aptemkov.tasksapp.data.repository.LocalTasksRepositoryImpl
import io.github.aptemkov.tasksapp.data.repository.RemoteTasksRepositoryImpl
import io.github.aptemkov.tasksapp.data.repository.SharedPrefsRepositoryImpl
import io.github.aptemkov.tasksapp.data.repository.TasksRepositoryImpl
import io.github.aptemkov.tasksapp.domain.audio.AudioPlayer
import io.github.aptemkov.tasksapp.domain.audio.AudioRecorder
import io.github.aptemkov.tasksapp.domain.repository.SharedPrefsRepository
import io.github.aptemkov.tasksapp.domain.repository.TasksRepository

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideProductsRepository(
        database: TasksDatabase,
        api: TasksApi,
        resourceProvider: ResourceProvider
    ): TasksRepository {
        return TasksRepositoryImpl(database, api, resourceProvider)
    }

    @Provides
    fun provideLocalRepository(
        database: TasksDatabase,
    ): LocalTasksRepositoryImpl {
        return LocalTasksRepositoryImpl(database)
    }

    @Provides
    fun provideRemoteRepository(
        api: TasksApi,
        resourceProvider: ResourceProvider
    ): RemoteTasksRepositoryImpl {
        return RemoteTasksRepositoryImpl(api, resourceProvider)
    }

    @Provides
    fun provideSharedPrefsRepository(
        sharedPreferences: SharedPreferences
    ): SharedPrefsRepository {
        return SharedPrefsRepositoryImpl(sharedPreferences)
    }

    @Provides
    fun provideAudioRecorder(
        @ApplicationContext context: Context,
    ): AudioRecorder {
        return AudioRecorderImpl(context)
    }

    @Provides
    fun provideAudioPlayer(
        @ApplicationContext context: Context,
    ): AudioPlayer {
        return AudioPlayerImpl(context)
    }

}