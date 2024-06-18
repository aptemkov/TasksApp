package io.github.aptemkov.tasksapp.ui.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val lightColorScheme = TasksColorScheme(
    supportSeparator = LightSupportSeparator,
    supportOverlay = LightSupportOverlay,
    labelPrimary = LightLabelPrimary,
    labelSecondary = LightLabelSecondary,
    labelTertiary = LightLabelTertiary,
    labelDisable = LightLabelDisable,
    red = LightRed,
    green = LightGreen,
    blue = LightBlue,
    gray = LightGray,
    grayLight = LightGrayLight,
    white = LightWhite,
    backPrimary = LightBackPrimary,
    backSecondary = LightBackSecondary,
    backElevated = LightBackElevated,
)

private val darkColorScheme = TasksColorScheme(
    supportSeparator = DarkSupportSeparator,
    supportOverlay = DarkSupportOverlay,
    labelPrimary = DarkLabelPrimary,
    labelSecondary = DarkLabelSecondary,
    labelTertiary = DarkLabelTertiary,
    labelDisable = DarkLabelDisable,
    red = DarkRed,
    green = DarkGreen,
    blue = DarkBlue,
    gray = DarkGray,
    grayLight = DarkGrayLight,
    white = DarkWhite,
    backPrimary = DarkBackPrimary,
    backSecondary = DarkBackSecondary,
    backElevated = DarkBackElevated,
)

private val typography = TasksTypography(
    titleLarge = TextStyle(
        fontWeight = FontWeight(500),
        fontSize = 32.sp,
        lineHeight = 37.5.sp,
    ),
    title = TextStyle(
        fontWeight = FontWeight(500),
        fontSize = 20.sp,
        lineHeight = 32.sp,
    ),
    button = TextStyle(
        fontWeight = FontWeight(500),
        fontSize = 14.sp,
        lineHeight = 24.sp,
    ),
    body = TextStyle(
        fontWeight = FontWeight(400),
        fontSize = 16.sp,
        lineHeight = 20.sp,
    ),
    subhead = TextStyle(
        fontWeight = FontWeight(400),
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
)

@Composable
fun TasksTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (isDarkTheme) darkColorScheme else lightColorScheme
    val rippleIndication = rememberRipple()
    CompositionLocalProvider(
        LocalTasksColorScheme provides colorScheme,
        LocalTasksTypography provides typography,
        LocalIndication provides rippleIndication,
        content = content
    )
}

object TasksTheme {

    val colorScheme: TasksColorScheme
        @Composable get() = LocalTasksColorScheme.current

    val typography: TasksTypography
        @Composable get() = LocalTasksTypography.current
}
