package io.github.aptemkov.tasksapp.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.aptemkov.tasksapp.data.repository.TasksRepositoryImpl
import io.github.aptemkov.tasksapp.domain.repository.TasksRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideProductsRepository(): TasksRepository {
        return TasksRepositoryImpl()
    }

}