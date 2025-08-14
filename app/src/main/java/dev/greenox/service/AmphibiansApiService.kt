package dev.greenox.service

import dev.greenox.model.AmphibiansModel
import retrofit2.http.GET

interface AmphibiansApiService {
    @GET(value = "/amphibians")
    suspend fun getData(): List<AmphibiansModel>
}