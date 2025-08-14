package dev.greenox.amphibians.fake

import dev.greenox.data.AmphibiansRepositoryImpl
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class NetworkAmphibiansRepositoryTest {

    @Test
    fun networkAmphibiansRepository_getAmphibians_verifyAmphibiansList() = runTest {
        val repository = AmphibiansRepositoryImpl(
            amphibiansApiService = FakeAmphibianApiService()
        )

        assertEquals(FakeDataSource.amphibiansList, repository.getData())
    }
}