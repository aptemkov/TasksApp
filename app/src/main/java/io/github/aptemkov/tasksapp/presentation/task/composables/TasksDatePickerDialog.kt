package io.github.aptemkov.tasksapp.presentation.task.composables

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.aptemkov.tasksapp.R
import io.github.aptemkov.tasksapp.ui.theme.TasksTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TasksDatePickerDialog(
    datePickerState: DatePickerState,
    onDismissRequest: () -> Unit,
    onConfirmButton: () -> Unit,
) {
    DatePickerDialog(
        onDismissRequest = { onDismissRequest() },
        confirmButton = {
            TextButton(onClick = { onConfirmButton() }) {
                Text(
                    text = stringResource(R.string.ok),
                    color = TasksTheme.colorScheme.labelPrimary
                )
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text(
                    text = stringResource(R.string.cancel),
                    color = TasksTheme.colorScheme.labelPrimary
                )
            }
        },
        colors = DatePickerDefaults.colors(
            containerColor = TasksTheme.colorScheme.backSecondary
        )
    ) {
        DatePicker(
            state = datePickerState,
            colors = DatePickerDefaults.colors(
                containerColor = TasksTheme.colorScheme.backSecondary,
                dayContentColor = TasksTheme.colorScheme.labelPrimary,
                weekdayContentColor = TasksTheme.colorScheme.labelSecondary,
                selectedDayContentColor = TasksTheme.colorScheme.labelPrimary,
                selectedDayContainerColor = TasksTheme.colorScheme.supportSeparator,
                todayDateBorderColor = Color.Transparent,
                todayContentColor = TasksTheme.colorScheme.blue,
                selectedYearContainerColor = TasksTheme.colorScheme.backSecondary,
                selectedYearContentColor = TasksTheme.colorScheme.labelSecondary,
                disabledSelectedYearContentColor = TasksTheme.colorScheme.labelSecondary,
                disabledSelectedYearContainerColor = TasksTheme.colorScheme.backSecondary,
                titleContentColor = TasksTheme.colorScheme.labelPrimary,
                subheadContentColor = TasksTheme.colorScheme.labelPrimary,
                currentYearContentColor = TasksTheme.colorScheme.labelSecondary,
                yearContentColor = TasksTheme.colorScheme.labelSecondary,
                disabledYearContentColor = TasksTheme.colorScheme.labelSecondary,
                navigationContentColor = TasksTheme.colorScheme.labelSecondary,
                headlineContentColor = TasksTheme.colorScheme.labelSecondary,
                dateTextFieldColors = TextFieldDefaults.colors(
                    focusedTextColor = TasksTheme.colorScheme.labelPrimary,
                    unfocusedTextColor = TasksTheme.colorScheme.labelPrimary,
                    unfocusedContainerColor = TasksTheme.colorScheme.backSecondary,
                    focusedContainerColor = TasksTheme.colorScheme.backSecondary,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    errorContainerColor = TasksTheme.colorScheme.backSecondary,
                    errorTextColor = TasksTheme.colorScheme.labelPrimary,
                    focusedPlaceholderColor = TasksTheme.colorScheme.labelSecondary,
                    unfocusedPlaceholderColor = TasksTheme.colorScheme.labelSecondary,
                    focusedSupportingTextColor = TasksTheme.colorScheme.labelSecondary,
                    disabledTextColor = TasksTheme.colorScheme.labelSecondary,
                )
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun LightDataPickerDialogPreview() {
    TasksTheme(isDarkTheme = false) {
        TasksDatePickerDialog(datePickerState = rememberDatePickerState(), onDismissRequest = { }, onConfirmButton = { })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun DarkDataPickerDialogPreview() {
    TasksTheme(isDarkTheme = true) {
        TasksDatePickerDialog(datePickerState = rememberDatePickerState(), onDismissRequest = { }, onConfirmButton = { })
    }
}