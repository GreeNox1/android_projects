package dev.greenox.marsphotos.fake

import dev.greenox.data.MarsPhotosRepository
import dev.greenox.model.MarsPhoto

class FakeNetworkMarsPhotosRepository : MarsPhotosRepository {
    override suspend fun getMarsPhotos(): List<MarsPhoto> {
        return FakeDataSource.photosList
    }
}