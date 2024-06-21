package io.github.aptemkov.tasksapp.presentation.task.composables

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.github.aptemkov.tasksapp.R

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
                Text(stringResource(R.string.ok))
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text(stringResource(R.string.cancel))
            }
        },
        colors = DatePickerDefaults.colors() // Переопределю цвета немного позже, потому что их тут достаточно много
    ) {
        DatePicker(state = datePickerState)
    }
}