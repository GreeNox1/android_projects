package dev.greenox.ui
import androidx.lifecycle.ViewModel
import dev.greenox.model.MenuItem
import dev.greenox.model.MenuItem.AccompanimentItem
import dev.greenox.model.MenuItem.EntreeItem
import dev.greenox.model.MenuItem.SideDishItem
import dev.greenox.model.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat

class OrderViewModel : ViewModel() {

    private val taxRate = 0.08

    private val _uiState = MutableStateFlow(value = OrderUiState())
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

    fun updateEntree(entree: EntreeItem) {
        val previousEntree = _uiState.value.entree
        updateItem(newItem = entree, previousItem = previousEntree)
    }

    fun updateSideDish(sideDish: SideDishItem) {
        val previousSideDish = _uiState.value.sideDish
        updateItem(newItem = sideDish, previousItem = previousSideDish)
    }

    fun updateAccompaniment(accompaniment: AccompanimentItem) {
        val previousAccompaniment = _uiState.value.accompaniment
        updateItem(newItem = accompaniment, previousItem =  previousAccompaniment)
    }

    fun resetOrder() {
        _uiState.value = OrderUiState()
    }

    private fun updateItem(newItem: MenuItem, previousItem: MenuItem?) {
        _uiState.update { currentState ->
            val previousItemPrice = previousItem?.price ?: 0.0
            val itemTotalPrice = currentState.itemTotalPrice - previousItemPrice + newItem.price
            val tax = itemTotalPrice * taxRate
            currentState.copy(
                itemTotalPrice = itemTotalPrice,
                orderTax = tax,
                orderTotalPrice = itemTotalPrice + tax,
                entree = newItem as? EntreeItem ?: currentState.entree,
                sideDish = newItem as? SideDishItem ?: currentState.sideDish,
                accompaniment = newItem as? AccompanimentItem ?: currentState.accompaniment
            )
        }
    }
}

fun Double.formatPrice(): String {
    return NumberFormat.getCurrencyInstance().format(this)
}