package io.github.aptemkov.tasksapp.presentation

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
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
class ChangeThemeTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    // Тест проверяет, открылся ли экран настроек (и есть ли на нём определенный элемент)
    @Test
    fun checkSettingsScreenExists() {
        with(composeTestRule) {
            onNodeWithTag(TEST_TAG_HOME_TOOLBAR_SETTINGS)
                .performClick()
            onNodeWithTag(TEST_TAG_SETTINGS_THEME_TITLE)
                .assertExists()
        }
    }

    // Тест проверяет, меняется ли цвет текста при смене темы устройства
    // Здесь я выбрал текст, цвет которого точно изменится при смене темы
    @Test
    fun checkThemeChanges() {
        with(composeTestRule) {
            onNodeWithTag(TEST_TAG_HOME_TOOLBAR_SETTINGS)
                .performClick()

            onNodeWithTag(TEST_TAG_SETTINGS_THEME_ITEM + "0")
                .performClick()
            val firstNode = composeTestRule.onNodeWithTag(TEST_TAG_SETTINGS_THEME_TITLE).fetchSemanticsNode()
            val firstColor = firstNode.getElementColor()

            Thread.sleep(50)

            onNodeWithTag(TEST_TAG_SETTINGS_THEME_ITEM + "1")
                .performClick()

            val secondNode = composeTestRule.onNodeWithTag(TEST_TAG_SETTINGS_THEME_TITLE).fetchSemanticsNode()
            val secondColor = secondNode.getElementColor()

            assert(firstColor != secondColor)
        }
    }

}