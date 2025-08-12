package dev.greenox.reply

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import dev.greenox.R
import dev.greenox.data.local.LocalEmailsDataProvider
import dev.greenox.ui.ReplyApp
import org.junit.Rule
import org.junit.Test

class ReplyAppStateRestorationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    @TestCompactWidth
    fun compactDevice_selectedEmailEmailRetained_afterConfigChange() {
        val stateRestorationTester = StateRestorationTester(composeTestRule)
        stateRestorationTester.setContent { ReplyApp(windowSize = WindowWidthSizeClass.Compact) }

        composeTestRule.onNodeWithText(
            text = composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)
        ).assertIsDisplayed()

        composeTestRule.onNodeWithText(
            text = composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].subject)
        ).performClick()

        composeTestRule.onNodeWithContentDescriptionForStringId(id = R.string.navigation_back).assertExists()
        composeTestRule.onNodeWithText(
            text = composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)
        ).assertExists()

        stateRestorationTester.emulateSavedInstanceStateRestore()

        composeTestRule.onNodeWithContentDescriptionForStringId(id = R.string.navigation_back).assertExists()
        composeTestRule.onNodeWithText(
            text = composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)
        ).assertExists()
    }

    @Test
    @TestExpandedWidth
    fun expandedDevice_selectedEmailEmailRetained_afterConfigChange() {
        val stateRestorationTester = StateRestorationTester(composeTestRule)
        stateRestorationTester.setContent { ReplyApp(windowSize = WindowWidthSizeClass.Expanded) }

        composeTestRule.onNodeWithText(
            text = composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)
        ).assertIsDisplayed()

        composeTestRule.onNodeWithText(
            text = composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].subject)
        ).performClick()

        composeTestRule
            .onNodeWithTagForStringId(id = R.string.details_screen)
            .onChildren()
            .assertAny(
                hasAnyDescendant(
                    matcher = hasText(
                        text = composeTestRule.activity.getString(
                            LocalEmailsDataProvider.allEmails[2].body
                        )
                    )
                )
            )

        stateRestorationTester.emulateSavedInstanceStateRestore()

        composeTestRule
            .onNodeWithTagForStringId(id = R.string.details_screen)
            .onChildren()
            .assertAny(
                hasAnyDescendant(
                    matcher = hasText(
                        text = composeTestRule.activity.getString(
                            LocalEmailsDataProvider.allEmails[2].body
                        )
                    )
                )
            )
    }
}