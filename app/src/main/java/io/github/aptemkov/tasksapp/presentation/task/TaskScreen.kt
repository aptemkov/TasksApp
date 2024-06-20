package io.github.aptemkov.tasksapp.presentation.task

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.aptemkov.tasksapp.R
import io.github.aptemkov.tasksapp.domain.models.Task
import io.github.aptemkov.tasksapp.ui.theme.TasksTheme
import java.util.Calendar

@Composable
fun TaskScreen(
    id: String?,
    onNewTaskAdd: (Task) -> Unit,
) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(TasksTheme.colorScheme.backPrimary)
                    .padding(top = 32.dp)
                    .padding(all = 16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_close),
                    tint = TasksTheme.colorScheme.labelPrimary,
                    contentDescription = stringResource(R.string.close_button)
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = stringResource(R.string.save),
                    style = TasksTheme.typography.button,
                    color = TasksTheme.colorScheme.blue,
                )
            }

        },
    ) { innerPadding ->
        TaskScreenContent(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreenContent(
    modifier: Modifier,
) {
    var description by rememberSaveable { mutableStateOf("") }
    var deadline by rememberSaveable { mutableLongStateOf(0L) }
    var checked by rememberSaveable { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    Box() {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(TasksTheme.colorScheme.backPrimary)
                .padding(16.dp)
        ) {

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(104.dp),
                    value = description,
                    onValueChange = { description = it },
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

            Spacer(modifier = Modifier.height(12.dp))

            PriorityDropDownMenu()

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = TasksTheme.colorScheme.backElevated
            )

            Spacer(modifier = Modifier.height(12.dp))

            TasksDatePicker(checked = checked, onCheckedChanged = { checked = it })

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = TasksTheme.colorScheme.backElevated
            )

            Spacer(modifier = Modifier.height(12.dp))

            RemoveRow(isEnabled = false)

        }
        if(checked) {
            DatePickerDialog(
                onDismissRequest = { checked = false },
                confirmButton = {
                    TextButton(
                        onClick = {
                            deadline = datePickerState.selectedDateMillis ?: 0L
                            checked = false
                        }
                    ) { Text(stringResource(R.string.ok)) }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            checked = false
                        }
                    ) { Text(stringResource(R.string.cancel)) }
                },
                colors = DatePickerDefaults.colors()
            )
            {
                DatePicker(state = datePickerState)
            }
        }
    }
}
