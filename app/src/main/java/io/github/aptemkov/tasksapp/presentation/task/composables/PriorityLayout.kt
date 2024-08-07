package io.github.aptemkov.tasksapp.presentation.task.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.aptemkov.tasksapp.R
import io.github.aptemkov.tasksapp.domain.models.Priority
import io.github.aptemkov.tasksapp.domain.models.textName
import io.github.aptemkov.tasksapp.ui.theme.TasksTheme

@Composable
fun PriorityLayout(
    modifier: Modifier = Modifier,
    selectedPriority: Priority,
    changeVisibility: (Boolean) -> Unit,
) {
    val priorityColor = animateColorAsState(
        targetValue = when(selectedPriority) {
            Priority.HIGH -> TasksTheme.colorScheme.red
            else -> TasksTheme.colorScheme.labelTertiary
        },
        label = stringResource(R.string.selected_priority_animation),
    )

    Column(
        modifier = modifier
            .padding(vertical = 16.dp)
            .background(TasksTheme.colorScheme.backPrimary)
            .clickable { changeVisibility(true) }
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
            color = priorityColor.value
        )
    }
}

@Preview
@Composable
private fun LightDropDownMenuPreview() {
    TasksTheme(isDarkTheme = false) {
        PriorityLayout(
            modifier = Modifier,
            selectedPriority = Priority.HIGH,
            changeVisibility = { }
        )
    }
}

@Preview
@Composable
private fun DarkDropDownMenuPreview() {
    TasksTheme(isDarkTheme = true) {
        PriorityLayout(
            modifier = Modifier,
            selectedPriority = Priority.LOW,
            changeVisibility = { }
        )
    }
}
