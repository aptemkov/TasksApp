package io.github.aptemkov.tasksapp.data.repository

import android.content.SharedPreferences
import io.github.aptemkov.tasksapp.app.utils.AppThemesEnum
import io.github.aptemkov.tasksapp.app.utils.SYSTEM_THEME_PREFS
import io.github.aptemkov.tasksapp.app.utils.SYSTEM_THEME_PREFS_SYSTEM
import io.github.aptemkov.tasksapp.app.utils.getPrefsValueByTheme
import io.github.aptemkov.tasksapp.app.utils.getThemeByPrefsValue
import io.github.aptemkov.tasksapp.domain.repository.SharedPrefsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefsRepositoryImpl @Inject  constructor(
    private val sharedPreferences: SharedPreferences
): SharedPrefsRepository {

    private val _themeValue = MutableStateFlow(getThemeValue())
    val themeValue: StateFlow<AppThemesEnum> = _themeValue

    private val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        if (key == SYSTEM_THEME_PREFS) {
            _themeValue.update { getThemeValue() }
        }
    }

    init {
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
    }

    override fun setThemeValue(theme: AppThemesEnum) {
        val prefsValue = getPrefsValueByTheme(theme)

        sharedPreferences.edit()
            .putString(SYSTEM_THEME_PREFS, prefsValue)
            .apply()
    }

    override fun getThemeValue(): AppThemesEnum {
        val prefsValue = sharedPreferences
            .getString(SYSTEM_THEME_PREFS, SYSTEM_THEME_PREFS_SYSTEM) ?: SYSTEM_THEME_PREFS_SYSTEM
        return getThemeByPrefsValue(prefsValue)
    }

    override fun observeThemeValue(): Flow<AppThemesEnum> {
        return themeValue
    }

}