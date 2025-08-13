package dev.greenox.network

import dev.greenox.model.MarsPhoto
import retrofit2.http.GET

interface MarsApiService {
    @GET(value = "photos")
    suspend fun getPhotos(): List<MarsPhoto>
}