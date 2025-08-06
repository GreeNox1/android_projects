package dev.greenox.ui

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModel
import dev.greenox.R
import dev.greenox.data.DataSource.desserts
import dev.greenox.data.DessertClickerUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertClickerViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(value = DessertClickerUiState())
    val uiState: StateFlow<DessertClickerUiState> = _uiState.asStateFlow()

    fun onDessertClicked() {
        _uiState.update { currentState ->
            val dessertsSold = uiState.value.dessertsSold + 1
            val nextDessertIndex = determineDessertIndex(dessertsSold)
            currentState.copy(
                revenue = uiState.value.revenue + uiState.value.currentDessertPrice,
                dessertsSold = dessertsSold,
                currentDessertImageId = desserts[nextDessertIndex].imageId,
                currentDessertPrice = desserts[nextDessertIndex].price,
            )
        }
    }
    private fun determineDessertIndex(dessertsSold: Int): Int {
        var dessertIndex = 0
        for (index in desserts.indices) {
            if (dessertsSold >= desserts[index].startProductionAmount) {
                dessertIndex = index
            } else {
                break
            }
        }

        return dessertIndex
    }

    fun shareSoldDessertsInformation(intentContext: Context, dessertsSold: Int, revenue: Int) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                intentContext.getString(R.string.share_text, dessertsSold, revenue)
            )
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)

        try {
            intentContext.startActivity(shareIntent, null)
        } catch (_: ActivityNotFoundException) {

            Toast.makeText(
                intentContext,
                intentContext.getString(R.string.sharing_not_available),
                Toast.LENGTH_LONG
            ).show()
        }
    }

}