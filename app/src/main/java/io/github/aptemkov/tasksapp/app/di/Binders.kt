package io.github.aptemkov.tasksapp.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.aptemkov.tasksapp.app.providers.ResourceProvider
import io.github.aptemkov.tasksapp.app.providers.ResourceProviderImpl
import io.github.aptemkov.tasksapp.data.repository.LocalTasksRepositoryImpl
import io.github.aptemkov.tasksapp.data.repository.RemoteTasksRepositoryImpl
import io.github.aptemkov.tasksapp.domain.repository.LocalTasksRepository
import io.github.aptemkov.tasksapp.domain.repository.RemoteTasksRepository


@Module
@InstallIn(SingletonComponent::class)
abstract class ResourceModule {

    @Binds
    abstract fun bindResourceProvider(
        resourceProviderImpl: ResourceProviderImpl
    ): ResourceProvider

    @Binds
    abstract fun provideLocalRepository(
        localRepository: LocalTasksRepositoryImpl,
    ): LocalTasksRepository

    @Binds
    abstract fun provideRemoteRepository(
        remoteRepository: RemoteTasksRepositoryImpl
    ): RemoteTasksRepository

}