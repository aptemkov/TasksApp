package io.github.aptemkov.tasksapp.app.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.aptemkov.tasksapp.data.database.TasksDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideTasksDatabase(
        @ApplicationContext context: Context
    ): TasksDatabase {
        return TasksDatabase(context)
    }

}