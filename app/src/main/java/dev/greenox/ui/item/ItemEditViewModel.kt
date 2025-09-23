package dev.greenox.ui.item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.greenox.data.ItemsRepository
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ItemEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val itemsRepository: ItemsRepository,
) : ViewModel() {

    var itemUiState by mutableStateOf(value = ItemUiState())
        private set

    private val itemId: Int = checkNotNull(savedStateHandle[ItemEditDestination.ITEM_ID_ARG])

    private fun validateInput(uiState: ItemDetails = itemUiState.itemDetails): Boolean {
        return with(receiver = uiState) {
            name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
        }
    }

    init {
        viewModelScope.launch {
            itemUiState = itemsRepository
                .getItemStream(itemId)
                .filterNotNull()
                .first()
                .toItemUiState(isEntryValid = true)
        }
    }

    fun updateUiState(itemDetails: ItemDetails) {
        itemUiState = ItemUiState(
            itemDetails = itemDetails,
            isEntryValid = validateInput(uiState = itemDetails),
        )
    }

    suspend fun updateItem() {
        if (validateInput(uiState = itemUiState.itemDetails)) {
            itemsRepository.updateItem(item = itemUiState.itemDetails.toItem())
        }
    }
}