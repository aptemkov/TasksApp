package io.github.aptemkov.tasksapp.presentation.home.composables

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.aptemkov.tasksapp.R
import io.github.aptemkov.tasksapp.ui.theme.TasksTheme

@Composable
fun HomeFab(onNewTaskClick: () -> Unit) {
    FloatingActionButton(
        containerColor = TasksTheme.colorScheme.blue,
        contentColor = TasksTheme.colorScheme.white,
        onClick = { onNewTaskClick() }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_plus),
            contentDescription = stringResource(R.string.new_task)
        )
    }
}

@Preview
@Composable
private fun LightHomeFabPreview() {
    TasksTheme(isDarkTheme = false) {
        HomeFab {}
    }
}

@Preview
@Composable
private fun DarkHomeFabPreview() {
    TasksTheme(isDarkTheme = true) {
        HomeFab {}
    }
}