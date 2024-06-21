package io.github.aptemkov.tasksapp.presentation.home

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
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
    changeVisibility: () -> Unit,
    onItemClick: (String) -> Unit,
    onChangeTaskIsDone: (String, Boolean) -> Unit,
    onNewTaskClick: () -> Unit,
    onDetailsClick: (String) -> Unit,
) {
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
            HomeScreenCollapsingToolBar(
                scrollBehavior = scrollBehavior,
                completedTasksNumber = uiState.completedTasksNumber,
                showCompletedTasks = uiState.showCompletedTasks,
                changeVisibility = changeVisibility
            )
        },
        floatingActionButton = {
            HomeFab(onNewTaskClick)
        },
        containerColor = TasksTheme.colorScheme.backPrimary,
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { innerPadding ->
        HomeScreenContent(
            modifier = Modifier.padding(innerPadding),
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
    onChangeTaskIsDone: (String, Boolean) -> Unit,
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