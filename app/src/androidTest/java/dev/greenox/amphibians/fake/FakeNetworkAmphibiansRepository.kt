package dev.greenox.amphibians.fake

import dev.greenox.data.AmphibiansRepository
import dev.greenox.model.AmphibiansModel

class FakeNetworkAmphibiansRepository : AmphibiansRepository {
    override suspend fun getData(): List<AmphibiansModel> {
        return FakeDataSource.amphibiansList
    }
}