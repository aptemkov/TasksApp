package io.github.aptemkov.tasksapp.presentation.task.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.aptemkov.tasksapp.R
import io.github.aptemkov.tasksapp.domain.models.Priority
import io.github.aptemkov.tasksapp.domain.models.textName
import io.github.aptemkov.tasksapp.ui.theme.TasksTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PriorityBottomSheet(
    sheetState: SheetState,
    onDismiss: () -> Unit,
    onItemClick: (Priority) -> Unit,
) {

    ModalBottomSheet(
        containerColor = TasksTheme.colorScheme.backElevated,
        sheetState = sheetState,
        onDismissRequest = { onDismiss() }
    ) {
        Priority.entries.forEach { priority ->

            PriorityBottomSheetItem(
                priority = priority,
                onItemClick = {
                    onItemClick(it)
                    onDismiss()
                }
            )
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = TasksTheme.colorScheme.supportSeparator
            )

        }
    }

}

@Composable
fun PriorityBottomSheetItem(
    priority: Priority,
    onItemClick: (Priority) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(TasksTheme.colorScheme.backElevated)
            .clickable { onItemClick(priority) }
            .padding(32.dp)
    ) {
        if (priority == Priority.HIGH) {
            Icon(
                tint = TasksTheme.colorScheme.red,
                painter = painterResource(id = R.drawable.icon_important),
                contentDescription = stringResource(id = R.string.priority_high),
            )
            Spacer(modifier = Modifier.width(16.dp))
        }

        Text(
            text = priority.textName,
            style = TasksTheme.typography.button,
            color = if(priority == Priority.HIGH)
                TasksTheme.colorScheme.red else TasksTheme.colorScheme.labelPrimary
        )

    }
}