package io.github.aptemkov.tasksapp.app.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.aptemkov.tasksapp.BuildConfig
import io.github.aptemkov.tasksapp.app.utils.SYSTEM_THEME_PREFS
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

    @Provides
    fun provideSharedPrefs(
        @ApplicationContext context: Context,
    ): SharedPreferences = context.getSharedPreferences(SYSTEM_THEME_PREFS, Context.MODE_PRIVATE)
}