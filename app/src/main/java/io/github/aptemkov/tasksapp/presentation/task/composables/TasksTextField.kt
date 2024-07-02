package io.github.aptemkov.tasksapp.presentation.task.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.aptemkov.tasksapp.R
import io.github.aptemkov.tasksapp.ui.theme.TasksTheme

@Composable
fun TasksTextField(
    description: String,
    isEditingEnabled: Boolean,
    onValueChange: (String) -> Unit,
    isError: Boolean,
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = TasksTheme.colorScheme.backSecondary)
    ) {
        OutlinedTextField(
            shape = CardDefaults.shape,
            readOnly = !isEditingEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 104.dp, max = 300.dp),
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
            trailingIcon = {
                if(isError) {
                    Icon(
                        imageVector =  Icons.Filled.Warning,
                        contentDescription = stringResource(R.string.text_field_error),
                        tint = TasksTheme.colorScheme.red
                    )
                }
            }
        )
    }
}

@Preview
@Composable
private fun LightTasksTextFieldPreview() {
    TasksTheme(isDarkTheme = false) {
        TasksTextField(
            description = "Длинное описание задачи для того, чтобы проверить корректное отображение длинного текста, который может не поместиться в карточку.",
            isEditingEnabled = true,
            onValueChange = {},
            isError = false,
        )
    }
}

@Preview
@Composable
private fun DarkTasksTextFieldPreview() {
    TasksTheme(isDarkTheme = true) {
        TasksTextField(
            description = "Длинное описание задачи для того, чтобы проверить корректное отображение длинного текста, который может не поместиться в карточку.",
            isEditingEnabled = true,
            onValueChange = {},
            isError = true,
        )
    }
}