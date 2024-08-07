package io.github.aptemkov.tasksapp.presentation.home

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.aptemkov.tasksapp.domain.models.Priority
import io.github.aptemkov.tasksapp.domain.models.Task
import io.github.aptemkov.tasksapp.presentation.home.composables.HomeFab
import io.github.aptemkov.tasksapp.presentation.home.composables.HomeScreenCollapsingToolBar
import io.github.aptemkov.tasksapp.presentation.home.composables.NewTaskItem
import io.github.aptemkov.tasksapp.presentation.home.composables.TaskItem
import io.github.aptemkov.tasksapp.ui.theme.TasksTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    uiState: HomeScreenUiState,
    uiAlertState: String,
    changeVisibility: () -> Unit,
    onItemClick: (String) -> Unit,
    onChangeTaskIsDone: (Task, Boolean) -> Unit,
    onNewTaskClick: () -> Unit,
    onDetailsClick: (String) -> Unit,
    onSettingsClick: () -> Unit,
) {
    val context = LocalContext.current
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    LaunchedEffect(uiAlertState) {
        if(uiAlertState.isNotEmpty()) {
            Toast.makeText(context, uiAlertState, Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            HomeScreenCollapsingToolBar(
                modifier = Modifier.semantics {
                    traversalIndex = -1f
                },
                scrollBehavior = scrollBehavior,
                completedTasksNumber = uiState.completedTasksNumber,
                showCompletedTasks = uiState.showCompletedTasks,
                onSettingsClick = onSettingsClick,
                changeVisibility = changeVisibility
            )
        },
        floatingActionButton = {
            HomeFab(
                modifier = Modifier.semantics {
                    role = Role.Button
                    traversalIndex = 0f
                },
                onNewTaskClick = onNewTaskClick
            )
        },
        containerColor = TasksTheme.colorScheme.backPrimary,
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { innerPadding ->
        HomeScreenContent(
            modifier = Modifier.padding(innerPadding).semantics { traversalIndex = 1f },
            tasksList = uiState.tasksListFiltered,
            onItemClick = onItemClick,
            onNewTaskClick = onNewTaskClick,
            onDetailsClick = onDetailsClick,
            onChangeTaskIsDone = onChangeTaskIsDone,
        )
    }
}

@Composable
private fun HomeScreenContent(
    modifier: Modifier,
    tasksList: List<Task>,
    onItemClick: (String) -> Unit,
    onNewTaskClick: () -> Unit,
    onDetailsClick: (String) -> Unit,
    onChangeTaskIsDone: (Task, Boolean) -> Unit,
) {
    Card(
        modifier = modifier.padding(horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = TasksTheme.colorScheme.backSecondary)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(
                items = tasksList,
                key = { it.id }
            ) { task ->
                TaskItem(
                    modifier = Modifier.animateItem(),
                    task = task,
                    onClick = onItemClick,
                    onDetailsClick = onDetailsClick,
                    onChangeTaskIsDone = onChangeTaskIsDone
                )
            }
            item {
                NewTaskItem(onNewTaskClick)
            }
        }
    }
}

@Preview
@Composable
private fun LightHomeScreenPreview() {
    val state = HomeScreenUiState(
        tasksList = listOf(
            Task(id = "1", description = "Улыбнуться", priority = Priority.HIGH, deadline = 19291L, isDone = false, createDate = 0L, editDate = 0L),
            Task(id = "2", description = "Утром пресс качат, бегит, анжуманя, турник", priority = Priority.LOW, deadline = 0, isDone = true, createDate = 0L, editDate = 0L),
            Task(id = "3", description = "Купить что-то, где-то, зачем-то, но зачем не очень понятно, но точно чтобы показать как обрезается… и даже немного больше, чтобы точно заметить правильность", priority = Priority.DEFAULT, deadline = 1L, isDone = false, createDate = 0L, editDate = 0L),
            Task(id = "4", description = "Сходить в магазин за покупками", priority = Priority.DEFAULT, deadline = 0, isDone = true, createDate = 0L, editDate = 0L),
        ),
        showCompletedTasks = false,
    )

    TasksTheme(isDarkTheme = false) {
        HomeScreen(
            uiState = state,
            uiAlertState = "",
            changeVisibility = { },
            onItemClick = {},
            onChangeTaskIsDone = { str, bool ->},
            onNewTaskClick = { },
            onSettingsClick = {},
            onDetailsClick = {}
        )
    }
}

@Preview
@Composable
private fun DarkHomeScreenPreview() {
    val state = HomeScreenUiState(
        tasksList = listOf(
            Task(id = "1", description = "Улыбнуться", priority = Priority.HIGH, deadline = 19291L, isDone = false, createDate = 0L, editDate = 0L),
            Task(id = "2", description = "Утром пресс качат, бегит, анжуманя, турник", priority = Priority.LOW, deadline = 0, isDone = true, createDate = 0L, editDate = 0L),
            Task(id = "3", description = "Купить что-то, где-то, зачем-то, но зачем не очень понятно, но точно чтобы показать как обрезается… и даже немного больше, чтобы точно заметить правильность", priority = Priority.DEFAULT, deadline = 1L, isDone = false, createDate = 0L, editDate = 0L),
            Task(id = "4", description = "Сходить в магазин за покупками", priority = Priority.DEFAULT, deadline = 0, isDone = true, createDate = 0L, editDate = 0L),
        ),
        showCompletedTasks = true,
    )

    TasksTheme(isDarkTheme = true) {
        HomeScreen(
            uiState = state,
            uiAlertState = "",
            changeVisibility = { },
            onItemClick = {},
            onChangeTaskIsDone = { str, bool ->},
            onNewTaskClick = { },
            onSettingsClick = {},
            onDetailsClick = {}
        )
    }
}