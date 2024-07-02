package io.github.aptemkov.tasksapp.presentation.task.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.aptemkov.tasksapp.R
import io.github.aptemkov.tasksapp.domain.models.Priority
import io.github.aptemkov.tasksapp.domain.models.textName
import io.github.aptemkov.tasksapp.ui.theme.TasksTheme

@Composable
fun PriorityDropDownMenu(
    modifier: Modifier = Modifier,
    selectedPriority: Priority,
    isEditingEnabled: Boolean,
    onPriorityChange: (Priority) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .padding(vertical = 16.dp)
            .background(TasksTheme.colorScheme.backPrimary)
            .clickable { expanded = !expanded }
    ) {
        Text(
            text = stringResource(R.string.priority),
            style = TasksTheme.typography.body,
            color = TasksTheme.colorScheme.labelPrimary
        )
        
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = selectedPriority.textName,
            style = TasksTheme.typography.body,
            color = TasksTheme.colorScheme.labelTertiary
        )

        DropdownMenu(
            modifier = Modifier.background(TasksTheme.colorScheme.backElevated),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            Priority.entries.forEach { priority ->
                DropdownMenuItem(
                    modifier = Modifier.background(TasksTheme.colorScheme.backElevated),
                    leadingIcon = {
                        if (priority == Priority.HIGH) {
                            Icon(
                                tint = TasksTheme.colorScheme.red,
                                painter = painterResource(id = R.drawable.icon_important),
                                contentDescription = stringResource(id = R.string.priority_high),
                            )
                        }
                    },
                    text = {
                        Text(
                            text = priority.textName,
                            style = TasksTheme.typography.body,
                            color = if(priority == Priority.HIGH)
                                TasksTheme.colorScheme.red else TasksTheme.colorScheme.labelPrimary
                        )
                    },
                    onClick = {
                        if(isEditingEnabled) {
                            onPriorityChange(priority)
                        }
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun LightDropDownMenuPreview() {
    TasksTheme(isDarkTheme = false) {
        PriorityDropDownMenu(
            modifier = Modifier,
            selectedPriority = Priority.HIGH,
            isEditingEnabled = true,
            onPriorityChange = { },
        )
    }
}

@Preview
@Composable
private fun DarkDropDownMenuPreview() {
    TasksTheme(isDarkTheme = true) {
        PriorityDropDownMenu(
            modifier = Modifier,
            selectedPriority = Priority.LOW,
            isEditingEnabled = true,
            onPriorityChange = { },
        )
    }
}
