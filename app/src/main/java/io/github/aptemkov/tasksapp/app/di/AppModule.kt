package io.github.aptemkov.tasksapp.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.aptemkov.tasksapp.BuildConfig
import io.github.aptemkov.tasksapp.app.providers.ResourceProvider
import io.github.aptemkov.tasksapp.data.database.TasksDatabase
import io.github.aptemkov.tasksapp.data.remote.TasksApi
import io.github.aptemkov.tasksapp.data.repository.TasksRepositoryImpl
import io.github.aptemkov.tasksapp.domain.repository.TasksRepository
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideProductsRepository(
        database: TasksDatabase,
        api: TasksApi,
        resourceProvider: ResourceProvider
    ): TasksRepository {
        return TasksRepositoryImpl(database, api, resourceProvider)
    }

    @Provides
    @Singleton
    @Named("bearer_api_key")
    fun provideLastFmApiKey(): String = BuildConfig.BEARER_API_KEY

    @Provides
    @Singleton
    @Named("base_url")
    fun provideLastFmBaseUrl(): String = BuildConfig.TASKS_BASE_URL
}