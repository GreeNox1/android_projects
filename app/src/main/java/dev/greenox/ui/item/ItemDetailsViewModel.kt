package dev.greenox.ui.item

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.greenox.data.ItemsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ItemDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val itemsRepository: ItemsRepository,
) : ViewModel() {
    private val itemId: Int = checkNotNull(savedStateHandle[ItemDetailsDestination.ITEM_ID_ARG])

    val uiState: StateFlow<ItemDetailsUiState> = itemsRepository
        .getItemStream(itemId)
        .filterNotNull()
        .map {
            ItemDetailsUiState(
                itemDetails = it.toItemDetails(),
                outOfStock = it.quantity <= 0,
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = TIMEOUT_MILLIS),
            initialValue = ItemDetailsUiState(),
        )

    fun reduceQuantityByOne() {
        viewModelScope.launch {
            val currentItem = uiState.value.itemDetails.toItem()

            if (currentItem.quantity > 0) {
                itemsRepository.updateItem(
                    item = currentItem.copy(quantity = currentItem.quantity - 1),
                )
            }
        }
    }

    suspend fun deleteItem() {
        itemsRepository.deleteItem(item = uiState.value.itemDetails.toItem())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class ItemDetailsUiState(
    val outOfStock: Boolean = true,
    val itemDetails: ItemDetails = ItemDetails()
)