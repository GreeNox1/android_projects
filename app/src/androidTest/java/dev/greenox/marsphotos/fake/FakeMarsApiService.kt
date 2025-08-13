package dev.greenox.marsphotos.fake

import dev.greenox.model.MarsPhoto
import dev.greenox.network.MarsApiService

class FakeMarsApiService : MarsApiService {
    override suspend fun getPhotos(): List<MarsPhoto> {
        return FakeDataSource.photosList
    }
}