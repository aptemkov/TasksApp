package io.github.aptemkov.tasksapp.ui.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Stable
data class TasksColorScheme(
    val supportSeparator: Color,
    val supportOverlay: Color,
    val labelPrimary: Color,
    val labelSecondary: Color,
    val labelTertiary: Color,
    val labelDisable: Color,
    val red: Color,
    val green: Color,
    val blue: Color,
    val gray: Color,
    val grayLight: Color,
    val white: Color,
    val backPrimary: Color,
    val backSecondary: Color,
    val backElevated: Color,
)

@Stable
data class TasksTypography(
    val titleLarge: TextStyle,
    val title: TextStyle,
    val button: TextStyle,
    val body: TextStyle,
    val subhead: TextStyle,
)

val LocalTasksColorScheme = staticCompositionLocalOf {
    TasksColorScheme(
        supportSeparator = Color.Unspecified,
        supportOverlay = Color.Unspecified,
        labelPrimary = Color.Unspecified,
        labelSecondary = Color.Unspecified,
        labelTertiary = Color.Unspecified,
        labelDisable = Color.Unspecified,
        red = Color.Unspecified,
        green = Color.Unspecified,
        blue = Color.Unspecified,
        gray = Color.Unspecified,
        grayLight = Color.Unspecified,
        white = Color.Unspecified,
        backPrimary = Color.Unspecified,
        backSecondary = Color.Unspecified,
        backElevated = Color.Unspecified,
    )
}

val LocalTasksTypography = staticCompositionLocalOf {
    TasksTypography(
        titleLarge = TextStyle.Default,
        title = TextStyle.Default,
        button = TextStyle.Default,
        body = TextStyle.Default,
        subhead = TextStyle.Default,
    )
}
