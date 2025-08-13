package dev.greenox.marsphotos.fake

import dev.greenox.marsphotos.rules.TestDispatcherRule
import dev.greenox.ui.screen.MarsUiState
import dev.greenox.ui.screen.MarsViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class MarsViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun marsViewModel_getMarsPhotos_verifyMarsUiStateSuccess() = runTest {
        val marsViewModel = MarsViewModel(
            marsPhotosRepository = FakeNetworkMarsPhotosRepository()
        )

        assertEquals(
            MarsUiState.Success(
                photos = FakeDataSource.photosList
            ),
            marsViewModel.marsUiState
        )
    }
}