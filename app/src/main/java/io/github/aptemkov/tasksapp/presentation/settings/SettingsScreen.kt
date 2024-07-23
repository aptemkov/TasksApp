package io.github.aptemkov.tasksapp.presentation.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.aptemkov.tasksapp.R
import io.github.aptemkov.tasksapp.app.utils.AppThemesEnum
import io.github.aptemkov.tasksapp.presentation.settings.composables.SettingsToolbar
import io.github.aptemkov.tasksapp.presentation.settings.composables.ThemeLayout
import io.github.aptemkov.tasksapp.ui.theme.TasksTheme

@Composable
fun SettingsScreen(
    selectedTheme: AppThemesEnum,
    onThemeChange: (AppThemesEnum) -> Unit,
    onBack: () -> Unit,
    onDetails: () -> Unit,
) {
    Scaffold(
        topBar = {
            SettingsToolbar(
                onBack = onBack,
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(TasksTheme.colorScheme.backPrimary)
                .padding(16.dp)
                .verticalScroll(state = rememberScrollState()),
        ) {
            Spacer(Modifier.height(16.dp))

            ThemeLayout(selectedTheme, onThemeChange)

            Spacer(Modifier.height(16.dp))

            TextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onDetails() }
            ) {
                Text(
                    text = stringResource(R.string.about_this_app),
                    color = TasksTheme.colorScheme.labelSecondary,
                    style = TasksTheme.typography.button
                )
            }
        }
    }
}


