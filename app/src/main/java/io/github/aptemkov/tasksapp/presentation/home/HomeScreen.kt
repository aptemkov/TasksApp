package io.github.aptemkov.tasksapp.presentation.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.aptemkov.tasksapp.domain.models.Priority
import io.github.aptemkov.tasksapp.domain.models.Task
import io.github.aptemkov.tasksapp.ui.theme.TasksTheme

@Composable
fun HomeScreen(
    onClick: (String, String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TasksTheme.colorScheme.backPrimary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val list = listOf(
            Task(id = "1", description = "Задача 1", priority = Priority.LOW, deadline = 1L, isDone = false, createDate = 0L, editDate = 0L),
            Task(id = "2", description = "Задача 2", priority = Priority.LOW, deadline = 1L, isDone = true, createDate = 0L, editDate = 0L),

            Task(id = "3", description = "Купить что-то, где-то, зачем-то, но зачем не очень понятно, но точно чтобы показать как обрезается… и даже немного больше, чтобы точно заметить правильность", priority = Priority.DEFAULT, deadline = 1L, isDone = false, createDate = 0L, editDate = 0L),
            Task(id = "4", description = "Задача 4", priority = Priority.DEFAULT, deadline = 1L, isDone = true, createDate = 0L, editDate = 0L),

            Task(id = "5", description = "Задача 5", priority = Priority.HIGH, deadline = 1L, isDone = false, createDate = 0L, editDate = 0L),
            Task(id = "6", description = "Задача 6", priority = Priority.HIGH, deadline = 1L, isDone = true, createDate = 0L, editDate = 0L),
        )
        Card(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
                .background(TasksTheme.colorScheme.backSecondary),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        ) {
            list.forEach {
                TaskItem(task = it, onClick = {})
            }
        }

        Button(onClick = { onClick("Visit shop", "1") }) {
            Text(text = "To task")
        }
    }
}