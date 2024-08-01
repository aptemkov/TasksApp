package io.github.aptemkov.tasksapp

import io.github.aptemkov.tasksapp.data.repository.SharedPrefsRepositoryImpl
import android.content.SharedPreferences
import io.github.aptemkov.tasksapp.app.utils.AppThemesEnum
import io.github.aptemkov.tasksapp.app.utils.SYSTEM_THEME_PREFS
import io.github.aptemkov.tasksapp.app.utils.SYSTEM_THEME_PREFS_SYSTEM
import io.github.aptemkov.tasksapp.app.utils.getPrefsValueByTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class SharedPrefsRepositoryImplTest {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor
    private lateinit var repository: SharedPrefsRepositoryImpl

    @Before
    fun setUp() {
        sharedPreferences = mock(SharedPreferences::class.java)
        sharedPreferencesEditor = mock(SharedPreferences.Editor::class.java)

        `when`(sharedPreferences.edit()).thenReturn(sharedPreferencesEditor)
        `when`(sharedPreferencesEditor.putString(anyString(), anyString())).thenReturn(sharedPreferencesEditor)

        repository = SharedPrefsRepositoryImpl(sharedPreferences)
    }

    @Test
    fun `test setThemeValue updates sharedPreferences`() {
        val theme = AppThemesEnum.THEME_DARK

        repository.setThemeValue(theme)

        verify(sharedPreferencesEditor).putString(SYSTEM_THEME_PREFS, getPrefsValueByTheme(theme))
        verify(sharedPreferencesEditor).apply()
    }

    @Test
    fun `test getThemeValue returns correct theme`() {
        `when`(sharedPreferences.getString(SYSTEM_THEME_PREFS, SYSTEM_THEME_PREFS_SYSTEM))
            .thenReturn(getPrefsValueByTheme(AppThemesEnum.THEME_DARK))

        val theme = repository.getThemeValue()

        assertEquals(AppThemesEnum.THEME_DARK, theme)
    }

    @Test
    fun `test observeThemeValue emits initial value`() = runTest {
        `when`(sharedPreferences.getString(SYSTEM_THEME_PREFS, SYSTEM_THEME_PREFS_SYSTEM))
            .thenReturn(getPrefsValueByTheme(AppThemesEnum.THEME_SYSTEM))

        val initialTheme = repository.observeThemeValue().first()

        assertEquals(AppThemesEnum.THEME_SYSTEM, initialTheme)
    }
}
