package io.github.aptemkov.tasksapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.aptemkov.tasksapp.navigation.TasksAppNavHost
import io.github.aptemkov.tasksapp.ui.theme.TasksTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TasksTheme {
                val navController = rememberNavController()
                TasksAppNavHost(navController)
            }
        }
    }
}