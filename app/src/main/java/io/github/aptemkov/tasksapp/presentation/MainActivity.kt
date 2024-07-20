package io.github.aptemkov.tasksapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.aptemkov.tasksapp.app.utils.AppThemesEnum
import io.github.aptemkov.tasksapp.app.utils.getIsDarkThemeByTheme
import io.github.aptemkov.tasksapp.domain.repository.SharedPrefsRepository
import io.github.aptemkov.tasksapp.navigation.TasksAppNavHost
import io.github.aptemkov.tasksapp.ui.theme.TasksTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /**
     * Возможно, не лучшее решение инжектить репозиторий напрямую в активити, но есть ли смысл в создании
     * вьюмодели ради одного состояния темы?
     */
    @Inject
    lateinit var sharedPrefsRepository: SharedPrefsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val theme by sharedPrefsRepository.observeThemeValue()
                .collectAsStateWithLifecycle(initialValue = AppThemesEnum.THEME_SYSTEM)

            TasksTheme(
                isDarkTheme = theme.getIsDarkThemeByTheme()
            ) {
                val navController = rememberNavController()
                TasksAppNavHost(navController)
            }
        }
    }
}