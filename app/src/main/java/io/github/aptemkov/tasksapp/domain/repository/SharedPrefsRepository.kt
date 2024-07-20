package io.github.aptemkov.tasksapp.domain.repository

import io.github.aptemkov.tasksapp.app.utils.AppThemesEnum
import kotlinx.coroutines.flow.Flow

interface SharedPrefsRepository {

    fun setThemeValue(theme: AppThemesEnum)

    fun getThemeValue(): AppThemesEnum

    fun observeThemeValue(): Flow<AppThemesEnum>

}