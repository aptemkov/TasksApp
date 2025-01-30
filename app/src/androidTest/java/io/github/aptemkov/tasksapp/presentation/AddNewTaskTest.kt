package io.github.aptemkov.tasksapp.presentation

import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.github.aptemkov.tasksapp.presentation.utils.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class AddNewTaskTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
    }


    // Тест проверяет, появляется ли добавленная задача в списке
    @Test
    fun addTask_assertExistsInList() {
        val taskDescription = "Some task from test"

        with(composeTestRule) {

            onNodeWithTag(TEST_TAG_HOME_FAB)
                .performClick()

            onNodeWithTag(TEST_TAG_TASK_TEXT_FIELD)
                .performClick()
                .performTextInput(taskDescription)

            onNodeWithTag(TEST_TAG_TASK_SAVE_BTN)
                .performClick()

            onAllNodesWithText(taskDescription)
                .assertAny(hasText(taskDescription))

        }
    }


    // Тест проверяет, показывается ли иконка-предупреждение, если попытаться добавить задачу без описания
    @Test
    fun addTask_withoutDescription_assertWarningIconExists() {
        with(composeTestRule) {
            onNodeWithTag(TEST_TAG_HOME_FAB)
                .performClick()

            onNodeWithTag(TEST_TAG_TASK_SAVE_BTN)
                .performClick()

            Thread.sleep(100)

            onNodeWithTag(TEST_TAG_TASK_TEXT_FIELD_WARNING_ICON, useUnmergedTree = true)
                .assertExists()

        }
    }

}