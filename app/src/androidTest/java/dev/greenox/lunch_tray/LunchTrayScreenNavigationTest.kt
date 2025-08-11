package dev.greenox.lunch_tray

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import dev.greenox.LunchTrayApp
import dev.greenox.LunchTrayScreen
import dev.greenox.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LunchTrayScreenNavigationTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupLunchTrayNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(context = LocalContext.current).apply {
                navigatorProvider.addNavigator(navigator = ComposeNavigator())
            }
            LunchTrayApp(navController = navController)
        }
    }

    @Test
    fun lunchTrayNavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(LunchTrayScreen.Start.name)
    }

    @Test
    fun lunchTrayNavHost_verifyBackNavigationNotShownOnStartOrderScreen() {
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(label = backText).assertDoesNotExist()
    }

    @Test
    fun lunchTrayNavHost_click_navigatesToSelectEntreeMenuScreen() {
        navController.assertCurrentRouteName(LunchTrayScreen.Start.name)
        navigateToEntreeMenuScreen()
        navController.assertCurrentRouteName(LunchTrayScreen.EntreeMenu.name)
    }

    @Test
    fun lunchTrayNavHost_clickNextOnEntreeMenuScreen_navigateToSideDishMenuScreen() {
        navigateToSideDishMenuScreen()
        navController.assertCurrentRouteName(LunchTrayScreen.SideDishMenu.name)
    }

    @Test
    fun lunchTrayNavHost_clickNextOnSideDishMenuScreen_navigateToAccompanimentMenuScreen() {
        navigateToAccompanimentMenuScreen()
        navController.assertCurrentRouteName(LunchTrayScreen.AccompanimentMenu.name)
    }

    @Test
    fun lunchTrayNavHost_clickNextOnAccompanimentMenuScreen_navigateToCheckoutScreen() {
        navigateToCheckoutScreen()
        navController.assertCurrentRouteName(LunchTrayScreen.Checkout.name)
    }

    @Test
    fun lunchTrayNavHost_clickBackOnEntreeMenuScreen_navigateToStartOrderScreen() {
        navigateToEntreeMenuScreen()
        performNavigateUp()
        navController.assertCurrentRouteName(LunchTrayScreen.Start.name)
    }

    @Test
    fun lunchTrayNavHost_clickCancelOnEntreeMenuScreen_navigateToStartOrderScreen() {
        navigateToEntreeMenuScreen()
        composeTestRule.onNodeWithStringId(id = R.string.cancel).performClick()
        navController.assertCurrentRouteName(LunchTrayScreen.Start.name)
    }

    @Test
    fun lunchTrayNavHost_clickBackOnSideDishMenuScreen_navigateToEntreeMenuScreen() {
        navigateToSideDishMenuScreen()
        performNavigateUp()
        navController.assertCurrentRouteName(LunchTrayScreen.EntreeMenu.name)
    }

    @Test
    fun lunchTrayNavHost_clickCancelOnSideDishMenuScreen_navigateToStartOrderScreen() {
        navigateToSideDishMenuScreen()
        composeTestRule.onNodeWithStringId(id = R.string.cancel).performClick()
        navController.assertCurrentRouteName(LunchTrayScreen.Start.name)
    }

    @Test
    fun lunchTrayNavHost_clickBackOnAccompanimentMenuMenuScreen_navigateToSideDishMenuScreen() {
        navigateToAccompanimentMenuScreen()
        performNavigateUp()
        navController.assertCurrentRouteName(LunchTrayScreen.SideDishMenu.name)
    }

    @Test
    fun lunchTrayNavHost_clickCancelOnAccompanimentMenuMenuScreen_navigateToSideDishMenuScreen() {
        navigateToAccompanimentMenuScreen()
        composeTestRule.onNodeWithStringId(id = R.string.cancel).performClick()
        navController.assertCurrentRouteName(LunchTrayScreen.Start.name)
    }

    private fun navigateToEntreeMenuScreen() {
        composeTestRule.onNodeWithStringId(id = R.string.start_order).performClick()
        composeTestRule.onNodeWithContentDescription(label = "Cauliflower").performClick()
    }

    private fun navigateToSideDishMenuScreen() {
        navigateToEntreeMenuScreen()
        composeTestRule.onNodeWithStringId(id = R.string.next).performClick()
        composeTestRule.onNodeWithContentDescription(label = "Summer Salad").performClick()
    }

    private fun navigateToAccompanimentMenuScreen() {
        navigateToSideDishMenuScreen()
        composeTestRule.onNodeWithStringId(id = R.string.next).performClick()
        composeTestRule.onNodeWithContentDescription(label = "Lunch Roll").performClick()
    }

    private fun navigateToCheckoutScreen() {
        navigateToAccompanimentMenuScreen()
        composeTestRule.onNodeWithStringId(id = R.string.next).performClick()
    }

    private fun performNavigateUp() {
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(label = backText).performClick()
    }
}
