package dev.greenox.ui.screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import retrofit2.HttpException
import dev.greenox.MarsPhotosApplication
import dev.greenox.data.MarsPhotosRepository
import dev.greenox.model.MarsPhoto
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MarsUiState {
    data class Success(val photos: List<MarsPhoto>) : MarsUiState
    object Error: MarsUiState
    object Loading: MarsUiState
}

class MarsViewModel(
    private val marsPhotosRepository: MarsPhotosRepository,
) : ViewModel() {
    var marsUiState: MarsUiState by mutableStateOf(value = MarsUiState.Loading)
        private set

    init {
        getMarsPhotos()
    }

    fun getMarsPhotos() {
        viewModelScope.launch {
            marsUiState = MarsUiState.Loading
            marsUiState = try {
                MarsUiState.Success(photos = marsPhotosRepository.getMarsPhotos())
            } catch (e: IOException) {
                Log.d("MarsViewModel", "IOException: $e")
                MarsUiState.Error
            } catch (e: HttpException) {
                Log.d("MarsViewModel", "HttpException: $e")
                MarsUiState.Error
            }
        }
        Log.d("Info", "State: $marsUiState")
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MarsPhotosApplication)
                val marsPhotosRepository = application.container.marsPhotosRepository
                MarsViewModel(marsPhotosRepository = marsPhotosRepository)
            }
        }
    }
}