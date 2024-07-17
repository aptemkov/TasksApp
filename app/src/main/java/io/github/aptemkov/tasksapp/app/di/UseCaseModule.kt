package io.github.aptemkov.tasksapp.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.aptemkov.tasksapp.data.usecase.AddTaskUseCaseImpl
import io.github.aptemkov.tasksapp.data.usecase.ChangeTaskIsDoneUseCaseImpl
import io.github.aptemkov.tasksapp.data.usecase.EditTaskUseCaseImpl
import io.github.aptemkov.tasksapp.data.usecase.GetAllTasksUseCaseImpl
import io.github.aptemkov.tasksapp.data.usecase.GetTaskByIdUseCaseImpl
import io.github.aptemkov.tasksapp.data.usecase.MergeTasksUseCaseImpl
import io.github.aptemkov.tasksapp.data.usecase.RemoveTaskByIdUseCaseImpl
import io.github.aptemkov.tasksapp.domain.repository.LocalTasksRepository
import io.github.aptemkov.tasksapp.domain.repository.RemoteTasksRepository
import io.github.aptemkov.tasksapp.domain.usecase.AddTaskUseCase
import io.github.aptemkov.tasksapp.domain.usecase.ChangeTaskIsDoneUseCase
import io.github.aptemkov.tasksapp.domain.usecase.EditTaskUseCase
import io.github.aptemkov.tasksapp.domain.usecase.GetAllTasksUseCase
import io.github.aptemkov.tasksapp.domain.usecase.GetTaskByIdUseCase
import io.github.aptemkov.tasksapp.domain.usecase.MergeTasksUseCase
import io.github.aptemkov.tasksapp.domain.usecase.RemoveTaskByIdUseCase


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideAddTaskUseCase(
        localRepository: LocalTasksRepository,
        remoteRepository: RemoteTasksRepository
    ): AddTaskUseCase {
        return AddTaskUseCaseImpl(localRepository, remoteRepository)
    }

    @Provides
    fun provideChangeTaskDoneUseCase(
        localRepository: LocalTasksRepository,
        remoteRepository: RemoteTasksRepository
    ): ChangeTaskIsDoneUseCase {
        return ChangeTaskIsDoneUseCaseImpl(localRepository, remoteRepository)
    }

    @Provides
    fun provideDeleteTaskUseCase(
        localRepository: LocalTasksRepository,
        remoteRepository: RemoteTasksRepository
    ): RemoveTaskByIdUseCase {
        return RemoveTaskByIdUseCaseImpl(localRepository, remoteRepository)
    }

    @Provides
    fun provideEditTaskUseCase(
        localRepository: LocalTasksRepository,
        remoteRepository: RemoteTasksRepository
    ): EditTaskUseCase {
        return EditTaskUseCaseImpl(localRepository, remoteRepository)
    }

    @Provides
    fun provideGetAllTasksUseCase(localRepository: LocalTasksRepository): GetAllTasksUseCase {
        return GetAllTasksUseCaseImpl(localRepository)
    }

    @Provides
    fun provideGetTaskByIdUseCase(localRepository: LocalTasksRepository): GetTaskByIdUseCase {
        return GetTaskByIdUseCaseImpl(localRepository)
    }

    @Provides
    fun provideMergeTasksUseCase(
        localRepository: LocalTasksRepository,
        remoteRepository: RemoteTasksRepository
    ): MergeTasksUseCase {
        return MergeTasksUseCaseImpl(localRepository, remoteRepository)
    }
}
