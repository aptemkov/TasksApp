package io.github.aptemkov.tasksapp.presentation.task

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.aptemkov.tasksapp.R
import io.github.aptemkov.tasksapp.ui.theme.TasksTheme

@Composable
fun TasksDatePicker(checked: Boolean, onCheckedChanged: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = stringResource(R.string.deadline_text),
            style = TasksTheme.typography.body,
            color = TasksTheme.colorScheme.labelPrimary,
        )

        Spacer(modifier = Modifier.weight(1f))

        Switch(checked = checked, onCheckedChange = { onCheckedChanged(it) })
    }
}