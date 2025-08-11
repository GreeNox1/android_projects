package dev.greenox.lunch_tray

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import dev.greenox.R
import dev.greenox.data.DataSource
import dev.greenox.model.OrderUiState
import dev.greenox.ui.AccompanimentMenuScreen
import dev.greenox.ui.CheckoutScreen
import dev.greenox.ui.EntreeMenuScreen
import dev.greenox.ui.SideDishMenuScreen
import org.junit.Rule
import org.junit.Test

class LunchTrayOrderScreenTest {

    @get: Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val fakeOrderUiState = OrderUiState(
        entree = DataSource.entreeMenuItems.first(),
        sideDish = DataSource.sideDishMenuItems.first(),
        accompaniment = DataSource.accompanimentMenuItems.first(),
        itemTotalPrice = 7.0,
        orderTax = 5.5,
        orderTotalPrice = 4.0,
    )

    @Test
    fun entreeMenuScreen_verifyContent() {
        composeTestRule.setContent {
            EntreeMenuScreen(
                onSelectionChanged = {},
                onNextButtonClicked = {},
                onCancelButtonClicked = {},
                options = DataSource.entreeMenuItems,
            )
        }

        DataSource.entreeMenuItems.forEach {
            composeTestRule.onNodeWithContentDescription(label = it.name).assertIsDisplayed()
        }

        composeTestRule.onNodeWithStringId(id = R.string.next).assertIsNotEnabled()
    }

    @Test
    fun entreeMenuScreen_entreeSelected_NextButtonEnabled() {
        entreeMenuScreen_verifyContent()

        composeTestRule.onNodeWithContentDescription(label = DataSource.entreeMenuItems.first().name).performClick()

        composeTestRule.onNodeWithStringId(id = R.string.next).assertIsEnabled()
    }

    @Test
    fun sideDishMenuScreen_verifyContent() {
        composeTestRule.setContent {
            SideDishMenuScreen(
                onSelectionChanged = {},
                onNextButtonClicked = {},
                onCancelButtonClicked = {},
                options = DataSource.sideDishMenuItems,
            )
        }

        DataSource.sideDishMenuItems.forEach {
            composeTestRule.onNodeWithContentDescription(label = it.name).assertIsDisplayed()
        }

        composeTestRule.onNodeWithStringId(id = R.string.next).assertIsNotEnabled()
    }

    @Test
    fun sideDishMenuScreen_sideDishSelected_NextButtonEnabled() {
        sideDishMenuScreen_verifyContent()

        composeTestRule.onNodeWithContentDescription(label = DataSource.sideDishMenuItems.first().name).performClick()

        composeTestRule.onNodeWithStringId(id = R.string.next).assertIsEnabled()
    }

    @Test
    fun accompanimentMenuScreen_verifyContent() {
        composeTestRule.setContent {
            AccompanimentMenuScreen(
                onSelectionChanged = {},
                onNextButtonClicked = {},
                onCancelButtonClicked = {},
                options = DataSource.accompanimentMenuItems,
            )
        }

        DataSource.accompanimentMenuItems.forEach {
            composeTestRule.onNodeWithContentDescription(label = it.name).assertIsDisplayed()
        }

        composeTestRule.onNodeWithStringId(id = R.string.next).assertIsNotEnabled()
    }

    @Test
    fun accompanimentMenuScreen_accompanimentSelected_NextButtonEnabled() {
        accompanimentMenuScreen_verifyContent()

        composeTestRule.onNodeWithContentDescription(label = DataSource.accompanimentMenuItems.first().name).performClick()

        composeTestRule.onNodeWithStringId(id = R.string.next).assertIsEnabled()
    }

    @Test
    fun checkoutScreen_verifyContentDisplay() {
        composeTestRule.setContent {
            CheckoutScreen(
                onCancelButtonClicked = {},
                onNextButtonClicked = {},
                orderUiState = fakeOrderUiState,
            )
        }

        composeTestRule.onNodeWithText(text = fakeOrderUiState.entree?.name ?: "").assertIsDisplayed()
        composeTestRule.onNodeWithText(text = fakeOrderUiState.sideDish?.name ?: "").assertIsDisplayed()
        composeTestRule.onNodeWithText(text = fakeOrderUiState.accompaniment?.name ?: "").assertIsDisplayed()
    }
}