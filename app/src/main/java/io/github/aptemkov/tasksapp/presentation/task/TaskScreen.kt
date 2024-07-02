package io.github.aptemkov.tasksapp.presentation.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.aptemkov.tasksapp.domain.models.Priority
import io.github.aptemkov.tasksapp.presentation.task.composables.PriorityDropDownMenu
import io.github.aptemkov.tasksapp.presentation.task.composables.RemoveRow
import io.github.aptemkov.tasksapp.presentation.task.composables.TasksDatePicker
import io.github.aptemkov.tasksapp.presentation.task.composables.TasksDatePickerDialog
import io.github.aptemkov.tasksapp.presentation.task.composables.TasksTextField
import io.github.aptemkov.tasksapp.presentation.task.composables.TasksToolbar
import io.github.aptemkov.tasksapp.ui.theme.TasksTheme

@Composable
fun TaskScreen(
    uiState: TaskScreenUiState,
    tasksScreenArgument: TasksScreenArgument,
    onDescriptionChange: (String) -> Unit,
    onPriorityChange: (Priority) -> Unit,
    onDeadLineChange: (Long) -> Unit,
    onHasDeadLineChange: (Boolean) -> Unit,
    onNewTaskAdd: () -> Unit,
    onRemoveTask: () -> Unit,
    onLoadTask: (String) -> Unit,
    onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TasksToolbar(
                onBack = onBack,
                onNewTaskAdd = {
                    onNewTaskAdd()
                    if(uiState.description.isNotBlank()) {
                        onBack()
                    }
                }
            )
        },
    ) { innerPadding ->
        TaskScreenContent(
            modifier = Modifier.padding(innerPadding),
            uiState = uiState,
            tasksScreenArgument = tasksScreenArgument,
            onLoadTask = onLoadTask,
            onRemoveTask = onRemoveTask,
            onDescriptionChange = onDescriptionChange,
            onPriorityChange = onPriorityChange,
            onDeadLineChange = onDeadLineChange,
            onHasDeadLineChange = onHasDeadLineChange,
            onBack = onBack,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreenContent(
    modifier: Modifier,
    uiState: TaskScreenUiState,
    tasksScreenArgument: TasksScreenArgument,
    onLoadTask: (String) -> Unit,
    onRemoveTask: () -> Unit,
    onDescriptionChange: (String) -> Unit,
    onPriorityChange: (Priority) -> Unit,
    onDeadLineChange: (Long) -> Unit,
    onHasDeadLineChange: (Boolean) -> Unit,
    onBack: () -> Unit,
) {
    var showDatePicker by rememberSaveable { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = System.currentTimeMillis())
    val isTaskLoaded = tasksScreenArgument !is TasksScreenArgument.TaskNew
    val isEditingEnabled = tasksScreenArgument !is TasksScreenArgument.TaskDetails

    LaunchedEffect(Unit) {
        if(isTaskLoaded) {
            val id = tasksScreenArgument.getId()
            id?.let {
                onLoadTask(it)
            }
        }
    }

    Box {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(TasksTheme.colorScheme.backPrimary)
                .padding(16.dp)
                .verticalScroll(state = rememberScrollState()),
        ) {
            TasksTextField(
                description = uiState.description,
                isEditingEnabled = isEditingEnabled,
                isError = uiState.isDescriptionError,
                onValueChange = { onDescriptionChange(it) }
            )

            Spacer(modifier = Modifier.height(12.dp))

            PriorityDropDownMenu(
                selectedPriority = uiState.priority,
                isEditingEnabled = isEditingEnabled,
                onPriorityChange = onPriorityChange,
            )

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = TasksTheme.colorScheme.supportSeparator
            )

            Spacer(modifier = Modifier.height(12.dp))

            TasksDatePicker(
                hasDeadLine = uiState.hasDeadLine,
                deadLine = uiState.deadLine,
                onCheckedChanged = {
                    if(isEditingEnabled) {
                        onHasDeadLineChange(it)
                        showDatePicker = it
                    }
                },
                onDeadLineClicked = {
                    if(uiState.hasDeadLine) {
                        showDatePicker = true
                    }
                }
            )

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = TasksTheme.colorScheme.supportSeparator
            )

            Spacer(modifier = Modifier.height(12.dp))

            RemoveRow(
                isEnabled = isTaskLoaded,
                onRemoveTask = {
                    onRemoveTask()
                    onBack()
                }
            )

        }
        if (showDatePicker) {
            TasksDatePickerDialog(
                datePickerState = datePickerState,
                onDismissRequest = {
                    onDeadLineChange(datePickerState.selectedDateMillis ?: 0L)
                    showDatePicker = false
                },
                onConfirmButton = {
                    onDeadLineChange(datePickerState.selectedDateMillis ?: 0L)
                    showDatePicker = false
                },
            )
        }
    }
}
