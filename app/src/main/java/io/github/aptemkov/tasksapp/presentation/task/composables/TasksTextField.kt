package io.github.aptemkov.tasksapp.presentation.task.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.aptemkov.tasksapp.R
import io.github.aptemkov.tasksapp.ui.theme.TasksTheme

@Composable
fun TasksTextField(
    description: String,
    isEditingEnabled: Boolean,
    onValueChange: (String) -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            readOnly = !isEditingEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .height(104.dp),
            value = description,
            onValueChange = { onValueChange(it) },
            colors = TextFieldDefaults.colors(
                focusedTextColor = TasksTheme.colorScheme.labelPrimary,
                unfocusedTextColor = TasksTheme.colorScheme.labelPrimary,
                unfocusedContainerColor = TasksTheme.colorScheme.backSecondary,
                focusedContainerColor = TasksTheme.colorScheme.backSecondary,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            placeholder = {
                Text(
                    text = stringResource(R.string.enter_description_placeholder),
                    style = TasksTheme.typography.body,
                    color = TasksTheme.colorScheme.labelTertiary
                )
            },
        )
    }
}