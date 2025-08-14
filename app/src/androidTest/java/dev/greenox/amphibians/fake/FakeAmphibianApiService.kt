package dev.greenox.amphibians.fake

import dev.greenox.model.AmphibiansModel
import dev.greenox.service.AmphibiansApiService

class FakeAmphibianApiService : AmphibiansApiService {
    override suspend fun getData(): List<AmphibiansModel> {
        return FakeDataSource.amphibiansList
    }
}