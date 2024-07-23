package io.github.aptemkov.tasksapp.presentation.settings.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.aptemkov.tasksapp.R
import io.github.aptemkov.tasksapp.ui.theme.TasksTheme

@Composable
fun SettingsToolbar(
    onBack: () -> Unit,
) {
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
            contentDescription = stringResource(R.string.close_button),
            modifier = Modifier
                .focusable(true)
                .clickable { onBack() }
        )
        Spacer(modifier = Modifier.width(24.dp))
        Text(
            text = stringResource(R.string.settings_title),
            style = TasksTheme.typography.title,
            color = TasksTheme.colorScheme.labelPrimary,
        )
    }
}