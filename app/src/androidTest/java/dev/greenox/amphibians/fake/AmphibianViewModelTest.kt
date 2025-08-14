package dev.greenox.amphibians.fake

import dev.greenox.amphibians.rules.TestDispatcherRule
import dev.greenox.ui.screen.AmphibiansUiState
import dev.greenox.ui.screen.AmphibiansViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class AmphibianViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()


    @Test
    fun amphibianViewModel_getAmphibians_verifyAmphibianUiStateSuccess() = runTest {
        val amphibiansViewModel = AmphibiansViewModel(
            amphibiansRepository = FakeNetworkAmphibiansRepository()
        )

        assertEquals(
            AmphibiansUiState.Success(amphibians = FakeDataSource.amphibiansList),
            amphibiansViewModel.uiState
        )
    }
}