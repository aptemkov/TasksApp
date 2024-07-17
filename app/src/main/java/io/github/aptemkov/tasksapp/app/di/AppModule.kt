package io.github.aptemkov.tasksapp.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.aptemkov.tasksapp.BuildConfig
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Named("bearer_api_key")
    fun provideBearerApiKey(): String = BuildConfig.BEARER_API_KEY

    @Provides
    @Named("base_url")
    fun provideTasksBaseUrl(): String = BuildConfig.TASKS_BASE_URL
}