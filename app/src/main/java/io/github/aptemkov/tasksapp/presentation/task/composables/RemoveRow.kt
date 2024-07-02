package io.github.aptemkov.tasksapp.presentation.task.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.aptemkov.tasksapp.R
import io.github.aptemkov.tasksapp.ui.theme.TasksTheme

@Composable
fun RemoveRow(
    isEnabled: Boolean = false,
    onRemoveTask: () -> Unit,
) {
    val color = if (isEnabled) TasksTheme.colorScheme.red else TasksTheme.colorScheme.grayLight
    Row(
        modifier = Modifier
            .focusable(isEnabled)
            .clickable { if(isEnabled) onRemoveTask() }
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            tint = color,
            painter = painterResource(id = R.drawable.icon_delete),
            contentDescription = stringResource(R.string.delete_button),
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = stringResource(R.string.remove),
            style = TasksTheme.typography.body,
            color = color,
        )
    }
}

@Preview
@Composable
private fun LightTaskItemPreview() {
    TasksTheme(isDarkTheme = false) {
        RemoveRow {}
    }
}

@Preview
@Composable
private fun DarkTaskItemPreview() {
    TasksTheme(isDarkTheme = true) {
        RemoveRow(isEnabled = true) {}
    }
}