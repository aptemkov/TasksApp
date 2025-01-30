package io.github.aptemkov.tasksapp.presentation.settings

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.aptemkov.tasksapp.app.utils.AppThemesEnum
import io.github.aptemkov.tasksapp.domain.repository.SharedPrefsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val sharedPrefsRepository: SharedPrefsRepository,
): ViewModel() {

    private val _selectedTheme = MutableStateFlow(AppThemesEnum.THEME_SYSTEM)
    val selectedTheme = _selectedTheme.asStateFlow()

    init {
        selectTheme(sharedPrefsRepository.getThemeValue())
    }

    fun selectTheme(theme: AppThemesEnum) {
        _selectedTheme.update { theme }
        sharedPrefsRepository.setThemeValue(theme)
    }

}