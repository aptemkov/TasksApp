package io.github.aptemkov.tasksapp.presentation.home.composables

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.aptemkov.tasksapp.R
import io.github.aptemkov.tasksapp.presentation.utils.*
import io.github.aptemkov.tasksapp.ui.theme.TasksTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenCollapsingToolBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
    completedTasksNumber: Int,
    showCompletedTasks: Boolean,
    changeVisibility: () -> Unit,
    onSettingsClick: () -> Unit,
) {
    val isCollapsed = remember { derivedStateOf { scrollBehavior.state.collapsedFraction > 0.5 } }

    val textStyle =
        if (isCollapsed.value) TasksTheme.typography.title else TasksTheme.typography.titleLarge

    val elevation by animateDpAsState(targetValue = if (isCollapsed.value) 4.dp else 0.dp)

    LargeTopAppBar(
        modifier = modifier.shadow(elevation),
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.Bottom,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                ) {
                    Text(
                        modifier = Modifier.semantics {
                            traversalIndex = 1f
                        },
                        text = stringResource(R.string.my_tasks_title),
                        style = textStyle,
                        color = TasksTheme.colorScheme.labelPrimary
                    )
                    if (!isCollapsed.value) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            modifier = Modifier.semantics {
                                traversalIndex = 2f
                            },
                            text = stringResource(
                                R.string.completed_tasks_description,
                                completedTasksNumber
                            ),
                            style = TasksTheme.typography.body,
                            color = TasksTheme.colorScheme.labelTertiary
                        )
                    }
                }

                Icon(
                    tint = TasksTheme.colorScheme.blue,
                    imageVector = Icons.Default.Settings,
                    contentDescription = stringResource(id = R.string.settings_title),
                    modifier = Modifier
                        .testTag(TEST_TAG_HOME_TOOLBAR_SETTINGS)
                        .focusable()
                        .clickable { onSettingsClick() }
                        .semantics {
                            traversalIndex = 3f
                        },
                )

                Spacer(modifier = Modifier.width(16.dp))

                val eyeCheckBoxStateDescription = stringResource(id = R.string.show_hide_completed_tasks_button)
                val eyeCheckBoxContentDescription = stringResource(
                    id = if (showCompletedTasks)
                        R.string.show_hide_completed_tasks_button_enabled
                    else R.string.show_hide_completed_tasks_button_disabled
                )

                Icon(
                    tint = TasksTheme.colorScheme.blue,
                    painter = painterResource(
                        id = if (showCompletedTasks)
                            R.drawable.icon_state_not_visible else R.drawable.icon_status_visible
                    ),
                    contentDescription = eyeCheckBoxContentDescription,
                    modifier = Modifier
                        .testTag(TEST_TAG_HOME_TOOLBAR_EYE)
                        .clickable {
                            changeVisibility()
                        }
                        .semantics {
                            role = Role.Switch
                            traversalIndex = 4f
                            stateDescription = eyeCheckBoxStateDescription
                        },
                )
            }

        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = TasksTheme.colorScheme.backPrimary,
            scrolledContainerColor = TasksTheme.colorScheme.backPrimary,
            navigationIconContentColor = TasksTheme.colorScheme.labelPrimary,
            titleContentColor = TasksTheme.colorScheme.labelPrimary,
            actionIconContentColor = TasksTheme.colorScheme.labelPrimary,
        ),
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun LightToolBarPreview() {
    TasksTheme(isDarkTheme = false) {
        HomeScreenCollapsingToolBar(
            scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
                rememberTopAppBarState()
            ),
            completedTasksNumber = 7,
            showCompletedTasks = true,
            onSettingsClick = {},
            changeVisibility = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun DarkToolBarPreview() {
    TasksTheme(isDarkTheme = true) {
        HomeScreenCollapsingToolBar(
            scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
                rememberTopAppBarState()
            ),
            completedTasksNumber = 7,
            showCompletedTasks = false,
            onSettingsClick = {},
            changeVisibility = {}
        )
    }
}