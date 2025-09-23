package dev.greenox.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import dev.greenox.InventoryApplication
import dev.greenox.ui.home.HomeViewModel
import dev.greenox.ui.item.ItemDetailsViewModel
import dev.greenox.ui.item.ItemEditViewModel
import dev.greenox.ui.item.ItemEntryViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ItemEditViewModel(
                savedStateHandle = this.createSavedStateHandle(),
                itemsRepository = inventoryApplication().container.itemsRepository,
            )
        }
        initializer {
            ItemEntryViewModel(
                itemsRepository = inventoryApplication().container.itemsRepository,
            )
        }

        initializer {
            ItemDetailsViewModel(
                savedStateHandle = this.createSavedStateHandle(),
                itemsRepository = inventoryApplication().container.itemsRepository,
            )
        }

        initializer {
            HomeViewModel(
                itemsRepository = inventoryApplication().container.itemsRepository,
            )
        }
    }
}

fun CreationExtras.inventoryApplication(): InventoryApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as InventoryApplication)