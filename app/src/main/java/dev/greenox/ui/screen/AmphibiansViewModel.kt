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
import dev.greenox.app.App
import dev.greenox.data.AmphibiansRepository
import dev.greenox.model.AmphibiansModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface AmphibiansUiState {
    data class Success(val amphibians: List<AmphibiansModel>) : AmphibiansUiState
    object Error: AmphibiansUiState
    object Loading: AmphibiansUiState
}

class AmphibiansViewModel(
    private val amphibiansRepository: AmphibiansRepository,
) : ViewModel() {

    var uiState: AmphibiansUiState by mutableStateOf(value = AmphibiansUiState.Loading)
        private set

    init {
        getAmphibiansData()
    }

    fun getAmphibiansData() {
        viewModelScope.launch {
            uiState = AmphibiansUiState.Loading
            uiState = try {
                AmphibiansUiState.Success(amphibians = amphibiansRepository.getData())
            } catch (e: IOException) {
                Log.d("AmphibiansViewModel", "IOException: $e")
                AmphibiansUiState.Error
            } catch (e: HttpException) {
                Log.d("AmphibiansViewModel", "HttpException: $e")
                AmphibiansUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as App)
                val repository = application.container.amphibiansRepository
                AmphibiansViewModel(repository)
            }
        }
    }
}