package dev.greenox.data

import androidx.annotation.DrawableRes

data class DessertClickerUiState(
    val revenue: Int = 0,
    val dessertsSold: Int = 0,
    val currentDessertIndex: Int = 0,
    val currentDessertPrice: Int = DataSource.desserts[currentDessertIndex].price,
    @field:DrawableRes val currentDessertImageId: Int = DataSource.desserts[currentDessertIndex].imageId,
)