package io.github.aptemkov.tasksapp.presentation.home

import android.widget.Space
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
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.github.aptemkov.tasksapp.R
import io.github.aptemkov.tasksapp.domain.models.Priority
import io.github.aptemkov.tasksapp.domain.models.Task
import io.github.aptemkov.tasksapp.ui.theme.TasksTheme

@Composable
fun TaskItem(
    task: Task,
    onClick: (Task) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(TasksTheme.colorScheme.backSecondary)
            .focusable(true)
            .clickable { onClick(task) }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(24.dp),
            painter = when (task.isDone) {
                true -> painterResource(id = R.drawable.icon_checked)
                false -> {
                    if (task.priority == Priority.HIGH) painterResource(id = R.drawable.icon_unchecked_important_filled)
                    else painterResource(id = R.drawable.icon_unchecked)
                }
            },
            contentDescription = "",
        )

        Spacer(modifier = Modifier.width(12.dp))

        if (task.priority != Priority.DEFAULT) {
            Image(
                modifier = Modifier.size(24.dp),
                painter = when (task.priority) {
                    Priority.LOW -> painterResource(id = R.drawable.icon_unimportant)
                    else -> painterResource(id = R.drawable.icon_important)
                },
                contentDescription = "",
            )
            Spacer(modifier = Modifier.width(4.dp))
        }

        Column(modifier = Modifier.weight(1F)) {
            Text(
                modifier = Modifier,
                text = task.description,
                color = TasksTheme.colorScheme.labelPrimary,
                style = TasksTheme.typography.body,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
            )

            if (task.deadline != 0L) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    modifier = Modifier,
                    text = "До 16.07.2023",
                    color = TasksTheme.colorScheme.labelTertiary,
                    style = TasksTheme.typography.subhead,
                    maxLines = 1,
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = R.drawable.icon_info),
            colorFilter = ColorFilter.tint(TasksTheme.colorScheme.labelTertiary),
            contentDescription = ""
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