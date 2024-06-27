package io.github.aptemkov.tasksapp.presentation.task.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.aptemkov.tasksapp.R
import io.github.aptemkov.tasksapp.ui.theme.TasksTheme

@Composable
fun TasksToolbar(
    onBack: () -> Unit,
    onNewTaskAdd: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(TasksTheme.colorScheme.backPrimary)
            .padding(top = 32.dp)
            .padding(all = 16.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_close),
            tint = TasksTheme.colorScheme.labelPrimary,
            contentDescription = stringResource(R.string.close_button),
            modifier = Modifier
                .focusable(true)
                .clickable { onBack() }
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(R.string.save),
            style = TasksTheme.typography.button,
            color = TasksTheme.colorScheme.blue,
            modifier = Modifier
                .focusable()
                .clickable {
                    onNewTaskAdd()
                }
        )
    }
}

@Preview
@Composable
private fun LightTasksToolBarPreview() {
    TasksTheme(isDarkTheme = false) {
        TasksToolbar(onBack = { }, onNewTaskAdd = { })
    }
}

@Preview
@Composable
private fun DarkTasksToolBarPreview() {
    TasksTheme(isDarkTheme = true) {
        TasksToolbar(onBack = { }, onNewTaskAdd = { })
    }
}