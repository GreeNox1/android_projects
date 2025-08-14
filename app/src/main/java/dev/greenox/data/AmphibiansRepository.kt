package dev.greenox.data

import dev.greenox.model.AmphibiansModel
import dev.greenox.service.AmphibiansApiService

interface AmphibiansRepository {
    suspend fun getData() : List<AmphibiansModel>
}

class AmphibiansRepositoryImpl (
    private val amphibiansApiService: AmphibiansApiService,
) : AmphibiansRepository {
    override suspend fun getData(): List<AmphibiansModel> = amphibiansApiService.getData()
}