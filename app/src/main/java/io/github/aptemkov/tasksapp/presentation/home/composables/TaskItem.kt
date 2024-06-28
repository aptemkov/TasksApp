package io.github.aptemkov.tasksapp.presentation.home.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.aptemkov.tasksapp.R
import io.github.aptemkov.tasksapp.domain.models.Priority
import io.github.aptemkov.tasksapp.domain.models.Task
import io.github.aptemkov.tasksapp.domain.models.toDateString
import io.github.aptemkov.tasksapp.ui.theme.TasksTheme

@Composable
fun TaskItem(
    modifier: Modifier,
    task: Task,
    onClick: (String) -> Unit,
    onDetailsClick: (String) -> Unit,
    onChangeTaskIsDone: (String, Boolean) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(TasksTheme.colorScheme.backSecondary)
            .focusable(true)
            .clickable { onClick(task.id) }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    onChangeTaskIsDone(task.id, !task.isDone)
                },
            painter = when (task.isDone) {
                true -> painterResource(id = R.drawable.icon_checked)
                false -> {
                    if (task.priority == Priority.HIGH) painterResource(id = R.drawable.icon_unchecked_important_filled)
                    else painterResource(id = R.drawable.icon_unchecked)
                }
            },
            contentDescription = stringResource(R.string.is_task_done),
        )

        Spacer(modifier = Modifier.width(12.dp))

        if (task.priority != Priority.DEFAULT) {
            Image(
                modifier = Modifier.size(24.dp),
                painter = when (task.priority) {
                    Priority.LOW -> painterResource(id = R.drawable.icon_unimportant)
                    else -> painterResource(id = R.drawable.icon_important)
                },
                contentDescription = stringResource(id = R.string.priority),
            )
            Spacer(modifier = Modifier.width(4.dp))
        }

        val textDecoration = if(task.isDone) TextDecoration.LineThrough else null
        Column(modifier = Modifier.weight(1F)) {
            Text(
                modifier = Modifier,
                text = task.description,
                color = TasksTheme.colorScheme.labelPrimary,
                style = TasksTheme.typography.body.copy(textDecoration = textDecoration),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
            )

            if (task.deadline != 0L) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    modifier = Modifier,
                    text = stringResource(R.string.deadline_till, task.deadline.toDateString()),
                    color = TasksTheme.colorScheme.labelTertiary,
                    style = TasksTheme.typography.subhead.copy(textDecoration = textDecoration),
                    maxLines = 1,
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        Image(
            modifier = Modifier
                .size(24.dp)
                .focusable(true)
                .clickable { onDetailsClick(task.id) },
            painter = painterResource(id = R.drawable.icon_info),
            contentDescription = stringResource(R.string.details)
        )
    }
}

@Composable
fun NewTaskItem(
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(TasksTheme.colorScheme.backSecondary)
            .focusable(true)
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Spacer(modifier = Modifier.width(36.dp))
        Text(
            modifier = Modifier,
            text = stringResource(id = R.string.new_task),
            color = TasksTheme.colorScheme.labelTertiary,
            style = TasksTheme.typography.body,
        )
    }
}

@Preview
@Composable
private fun LightTaskItemPreview() {
    TasksTheme(isDarkTheme = false) {
        TaskItem(
            modifier = Modifier,
            task = Task(id = "1", description = "Купить продукты", priority = Priority.LOW, deadline = 0L, isDone = true),
            onClick = {  },
            onDetailsClick = { },
            onChangeTaskIsDone = { id, bool -> }
        )
    }
}

@Preview
@Composable
private fun DarkTaskItemPreview() {
    TasksTheme(isDarkTheme = true) {
        TaskItem(
            modifier = Modifier,
            task = Task(id = "2", description = "Длинное описание задачи для того, чтобы проверить корректное отображение длинного текста, который может не поместиться в карточку.", priority = Priority.HIGH, deadline = 447124712934L, isDone = false),
            onClick = {  },
            onDetailsClick = { },
            onChangeTaskIsDone = { id, bool -> }
        )
    }
}