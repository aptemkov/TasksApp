package io.github.aptemkov.tasksapp.presentation.settings.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.aptemkov.tasksapp.R
import io.github.aptemkov.tasksapp.app.utils.AppThemesEnum
import io.github.aptemkov.tasksapp.ui.theme.TasksTheme

@Composable
fun ThemeLayout(
    selectedTheme: AppThemesEnum,
    onThemeChange: (AppThemesEnum) -> Unit
) {
    Text(
        text = stringResource(id = R.string.app_theme),
        style = TasksTheme.typography.button,
        color = TasksTheme.colorScheme.labelTertiary
    )

    Spacer(Modifier.height(16.dp))

    ThemeRadioButtonLayout(selectedTheme, onThemeChange)
}
