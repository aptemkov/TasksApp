package io.github.aptemkov.tasksapp.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
* Данный файл нужен для предпросмотра цветов и стилей текста темы
*
*/

@Preview(heightDp = 600, widthDp = 1472)
@Composable
private fun LightThemeColorPreview() {
    TasksTheme(isDarkTheme = false) {
        ColorsPreview()
    }
}

@Preview(heightDp = 600, widthDp = 1472)
@Composable
private fun DarkThemeColorPreview() {
    TasksTheme(isDarkTheme = true) {
        ColorsPreview()
    }
}

@Preview
@Composable
private fun ColorStylesPreview() {
    TasksTheme {
        TextStylesPreview()
    }
}

@Composable
private fun ColorsPreview() {
    val rowModifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    Column(
        modifier = Modifier.background(Color(229,229,229)),
    ) {
        Row(modifier = rowModifier) {
            ColorBox(colorName = "supportSeparator", color = TasksTheme.colorScheme.supportSeparator)
            ColorBox(colorName = "supportOverlay", color = TasksTheme.colorScheme.supportOverlay)
        }
        Row(modifier = rowModifier) {
            ColorBox(colorName = "labelPrimary", color = TasksTheme.colorScheme.labelPrimary)
            ColorBox(colorName = "labelSecondary", color = TasksTheme.colorScheme.labelSecondary)
            ColorBox(colorName = "labelTertiary", color = TasksTheme.colorScheme.labelTertiary)
            ColorBox(colorName = "labelDisable", color = TasksTheme.colorScheme.labelDisable)
        }
        Row(modifier = rowModifier) {
            ColorBox(colorName = "red", color = TasksTheme.colorScheme.red)
            ColorBox(colorName = "green", color = TasksTheme.colorScheme.green)
            ColorBox(colorName = "blue", color = TasksTheme.colorScheme.blue)
            ColorBox(colorName = "gray", color = TasksTheme.colorScheme.gray)
            ColorBox(colorName = "grayLight", color = TasksTheme.colorScheme.grayLight)
            ColorBox(colorName = "white", color = TasksTheme.colorScheme.white)
        }
        Row(modifier = rowModifier) {
            ColorBox(colorName = "backPrimary", color = TasksTheme.colorScheme.backPrimary)
            ColorBox(colorName = "backSecondary", color = TasksTheme.colorScheme.backSecondary)
            ColorBox(colorName = "backElevated", color = TasksTheme.colorScheme.backElevated)
        }
    }
}

@Composable
private fun TextStylesPreview() {
    Column(
        modifier = Modifier.background(Color(229,229,229)),
    ) {
        TextBox(textStyle = TasksTheme.typography.titleLarge, text = "Large title - 32/38")
        TextBox(textStyle = TasksTheme.typography.title, text = "Title - 20/32")
        TextBox(textStyle = TasksTheme.typography.button, text = "BUTTON - 14/24")
        TextBox(textStyle = TasksTheme.typography.body, text = "Body - 16/20")
        TextBox(textStyle = TasksTheme.typography.subhead, text = "Subhead - 14/20")
    }
}

@Composable
private fun ColorBox(colorName: String, color: Color) {
    Column {
        Box(
            modifier = Modifier
                .size(height = 100.dp, width = 240.dp)
                .background(color),
        )
        Text(text = colorName)
    }
}

@Composable
private fun TextBox(textStyle: TextStyle, text: String) {
    Text(text = text, style = textStyle, modifier = Modifier.padding(16.dp))
}
