package io.github.aptemkov.tasksapp.presentation.task

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.aptemkov.tasksapp.domain.models.Task
import io.github.aptemkov.tasksapp.presentation.task.composables.PriorityDropDownMenu
import io.github.aptemkov.tasksapp.presentation.task.composables.RemoveRow
import io.github.aptemkov.tasksapp.presentation.task.composables.TasksDatePicker
import io.github.aptemkov.tasksapp.presentation.task.composables.TasksDatePickerDialog
import io.github.aptemkov.tasksapp.presentation.task.composables.TasksTextField
import io.github.aptemkov.tasksapp.presentation.task.composables.TasksToolbar
import io.github.aptemkov.tasksapp.ui.theme.TasksTheme

@Composable
fun TaskScreen(
    tasksScreenArgument: TasksScreenArgument,
    onNewTaskAdd: (Task) -> Unit,
    onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TasksToolbar(onBack, onNewTaskAdd)
        },
    ) { innerPadding ->
        TaskScreenContent(
            modifier = Modifier.padding(innerPadding),
            tasksScreenArgument = tasksScreenArgument,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreenContent(
    modifier: Modifier,
    tasksScreenArgument: TasksScreenArgument,
) {
    var description by rememberSaveable { mutableStateOf("") }
    var deadline by rememberSaveable { mutableLongStateOf(0L) }
    var checked by rememberSaveable { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val isDeleteRowEnabled = tasksScreenArgument !is TasksScreenArgument.TaskNew

    LaunchedEffect(Unit) {
        Log.i("TestTest", "TaskScreenContent: $tasksScreenArgument")
    }

    Box() {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(TasksTheme.colorScheme.backPrimary)
                .padding(16.dp)
        ) {

            TasksTextField(
                description = description,
                onValueChange = { description = it }
            )

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

            RemoveRow(isEnabled = isDeleteRowEnabled)

        }
        if (checked) {
            TasksDatePickerDialog(
                datePickerState = datePickerState,
                onDismissRequest = {
                    checked = false
                },
                onConfirmButton = {
                    deadline = datePickerState.selectedDateMillis ?: 0L
                    checked = false
                },
            )
        }
    }
}
