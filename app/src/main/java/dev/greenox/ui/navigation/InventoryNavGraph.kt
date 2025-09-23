package dev.greenox.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.greenox.ui.home.HomeDestination
import dev.greenox.ui.home.HomeScreen
import dev.greenox.ui.item.ItemDetailsDestination
import dev.greenox.ui.item.ItemDetailsScreen
import dev.greenox.ui.item.ItemEditDestination
import dev.greenox.ui.item.ItemEditScreen
import dev.greenox.ui.item.ItemEntryDestination
import dev.greenox.ui.item.ItemEntryScreen

@Composable
fun InventoryNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(route = ItemEntryDestination.route) },
                navigateToItemUpdate = {
                    navController.navigate(route = "${ItemDetailsDestination.route}/${it}")
                }
            )
        }
        composable(route = ItemEntryDestination.route) {
            ItemEntryScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
        composable(
            route = ItemDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(name = ItemDetailsDestination.ITEM_ID_ARG) {
                type = NavType.IntType
            })
        ) {
            ItemDetailsScreen(
                navigateToEditItem = { navController.navigate(route = "${ItemEditDestination.route}/$it") },
                navigateBack = { navController.navigateUp() }
            )
        }
        composable(
            route = ItemEditDestination.routeWithArgs,
            arguments = listOf(navArgument(name = ItemEditDestination.ITEM_ID_ARG) {
                type = NavType.IntType
            })
        ) {
            ItemEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}