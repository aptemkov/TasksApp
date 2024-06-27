package io.github.aptemkov.tasksapp.presentation.task.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.aptemkov.tasksapp.R
import io.github.aptemkov.tasksapp.domain.models.toDateString
import io.github.aptemkov.tasksapp.ui.theme.TasksTheme

@Composable
fun TasksDatePicker(
    hasDeadLine: Boolean,
    deadLine: Long,
    onCheckedChanged: (Boolean) -> Unit,
    onDeadLineClicked: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.clickable { onDeadLineClicked() }
        ) {
            Text(
                text = stringResource(R.string.deadline_text),
                style = TasksTheme.typography.body,
                color = TasksTheme.colorScheme.labelPrimary,
            )
            if (deadLine > 0L) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = deadLine.toDateString(),
                    style = TasksTheme.typography.body,
                    color = TasksTheme.colorScheme.blue,
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Switch(
            colors = SwitchDefaults.colors(
                checkedThumbColor = TasksTheme.colorScheme.blue,
                checkedTrackColor = TasksTheme.colorScheme.blue.copy(alpha = 0.3f),
                uncheckedThumbColor = TasksTheme.colorScheme.backElevated,
                uncheckedTrackColor = TasksTheme.colorScheme.grayLight.copy(alpha = 0.4f),
                checkedBorderColor = Color.Transparent,
                uncheckedBorderColor = Color.Transparent,
            ),
            checked = hasDeadLine,
            onCheckedChange = { onCheckedChanged(it) }
        )
    }
}

@Preview
@Composable
private fun DarkTaskItemPreview() {
    TasksTheme(isDarkTheme = true) {
        TasksDatePicker(hasDeadLine = true, deadLine = 1000000000L, onCheckedChanged = {}, onDeadLineClicked = {})
    }
}

@Preview
@Composable
private fun LightTaskItemPreview() {
    TasksTheme(isDarkTheme = false) {
        TasksDatePicker(hasDeadLine = false, deadLine = 0, onCheckedChanged = {}, onDeadLineClicked = {})
    }
}