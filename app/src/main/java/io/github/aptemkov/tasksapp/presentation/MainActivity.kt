package io.github.aptemkov.tasksapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import io.github.aptemkov.tasksapp.navigation.TasksAppNavHost
import io.github.aptemkov.tasksapp.presentation.home.HomeScreen
import io.github.aptemkov.tasksapp.presentation.task.TaskScreen
import io.github.aptemkov.tasksapp.ui.theme.TasksAppTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TasksAppTheme {
                val navController = rememberNavController()
                TasksAppNavHost(navController)
            }
        }
    }
}