package io.github.aptemkov.tasksapp.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.aptemkov.tasksapp.data.remote.TasksApi
import io.github.aptemkov.tasksapp.data.remote.utils.TasksAuthInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideTasksApi(
        @Named("base_url") baseUrl: String,
        okHttpClient: OkHttpClient,
    ): TasksApi {
        val jsonConverterFactory = Json.asConverterFactory("application/json".toMediaType())

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(jsonConverterFactory)
            .client(okHttpClient)
            .build()
            .create(TasksApi::class.java)
    }

    @Provides
    fun provideOkHttpClient(
        authInterceptor: TasksAuthInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    @Provides
    fun provideTasksAuthInterceptor(
        @Named("bearer_api_key") bearerToken: String
    ): TasksAuthInterceptor = TasksAuthInterceptor(bearerToken)

}