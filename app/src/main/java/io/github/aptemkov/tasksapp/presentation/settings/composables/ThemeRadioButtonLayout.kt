package io.github.aptemkov.tasksapp.presentation.settings.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.aptemkov.tasksapp.app.utils.AppThemesEnum
import io.github.aptemkov.tasksapp.app.utils.getStringResource
import io.github.aptemkov.tasksapp.presentation.utils.TEST_TAG_SETTINGS_THEME_ITEM
import io.github.aptemkov.tasksapp.ui.theme.TasksTheme

@Composable
fun ThemeRadioButtonLayout(
    selectedOption: AppThemesEnum,
    onThemeChange: (AppThemesEnum) -> Unit,
) {
    Column {
        AppThemesEnum.entries.forEachIndexed { index, appThemesEnum ->
            ThemeRadioButton(
                modifier = Modifier.testTag(TEST_TAG_SETTINGS_THEME_ITEM + index),
                theme = appThemesEnum,
                isSelected = appThemesEnum == selectedOption,
                onClick = { onThemeChange(it) }
            )
        }
    }
}

@Composable
fun ThemeRadioButton(
    modifier: Modifier,
    theme: AppThemesEnum,
    isSelected: Boolean,
    onClick: (AppThemesEnum) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(theme) }
    ) {
        RadioButton(
            colors = RadioButtonDefaults.colors(
                selectedColor = TasksTheme.colorScheme.blue,
                unselectedColor = TasksTheme.colorScheme.gray,
            ),
            selected = isSelected,
            onClick = { onClick(theme) }
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = stringResource(id = theme.getStringResource()),
            color = TasksTheme.colorScheme.labelSecondary
        )
    }
}