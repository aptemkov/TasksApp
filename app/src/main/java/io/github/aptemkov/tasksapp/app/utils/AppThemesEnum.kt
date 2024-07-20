package io.github.aptemkov.tasksapp.app.utils

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import io.github.aptemkov.tasksapp.R

enum class AppThemesEnum {
    THEME_DARK,
    THEME_LIGHT,
    THEME_SYSTEM,
}

fun getPrefsValueByTheme(themesEnum: AppThemesEnum): String {
    return when(themesEnum) {
        AppThemesEnum.THEME_SYSTEM -> SYSTEM_THEME_PREFS_SYSTEM
        AppThemesEnum.THEME_DARK -> SYSTEM_THEME_PREFS_DARK
        AppThemesEnum.THEME_LIGHT -> SYSTEM_THEME_PREFS_LIGHT
    }
}

fun getThemeByPrefsValue(prefsValue: String): AppThemesEnum {
    return when(prefsValue) {
        SYSTEM_THEME_PREFS_SYSTEM -> AppThemesEnum.THEME_SYSTEM
        SYSTEM_THEME_PREFS_DARK -> AppThemesEnum.THEME_DARK
        SYSTEM_THEME_PREFS_LIGHT -> AppThemesEnum.THEME_LIGHT
        else -> AppThemesEnum.THEME_SYSTEM
    }
}

@Composable
fun AppThemesEnum.getIsDarkThemeByTheme(): Boolean {
    return when (this) {
        AppThemesEnum.THEME_DARK -> true
        AppThemesEnum.THEME_LIGHT -> false
        AppThemesEnum.THEME_SYSTEM -> isSystemInDarkTheme()
    }
}

fun AppThemesEnum.getStringResource(): Int {
    return when(this) {
        AppThemesEnum.THEME_DARK -> R.string.app_theme_dark
        AppThemesEnum.THEME_LIGHT -> R.string.app_theme_light
        AppThemesEnum.THEME_SYSTEM -> R.string.app_theme_system
    }
}

const val SYSTEM_THEME_PREFS = "SYSTEM_THEME_PREFS"
const val SYSTEM_THEME_PREFS_DARK = "SYSTEM_THEME_PREFS_DARK"
const val SYSTEM_THEME_PREFS_LIGHT = "SYSTEM_THEME_PREFS_LIGHT"
const val SYSTEM_THEME_PREFS_SYSTEM = "SYSTEM_THEME_PREFS_SYSTEM"