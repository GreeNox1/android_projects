package dev.greenox.tiptime

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import dev.greenox.TipTimeLayout
import dev.greenox.ui.theme.TipTimeAppTheme
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat

class TipUiTests {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculate_20_percent_tip() {
        composeTestRule.setContent {
            TipTimeAppTheme {
                TipTimeLayout()
            }
        }
        composeTestRule
            .onNodeWithText(text = "Bill Amount")
            .performTextInput(text = "10")

        composeTestRule
            .onNodeWithText(text = "Tip Percentage")
            .performTextInput(text = "20")

        val expectedTip = NumberFormat.getCurrencyInstance().format(2)

        composeTestRule
            .onNodeWithText(text = "Tip Amount: $expectedTip")
            .assertExists("Bunday matnli node topilmadi")
    }
}