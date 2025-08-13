package dev.greenox.marsphotos.fake

import dev.greenox.model.MarsPhoto

object FakeDataSource {
    const val ID_ONE = "img1"
    const val ID_TWO = "img2"
    const val IMG_ONE = "url.1"
    const val IMG_TWO = "url.2"
    val photosList = listOf(
        MarsPhoto(
            id = ID_ONE,
            imgSrc = IMG_ONE
        ),
        MarsPhoto(
            id = ID_TWO,
            imgSrc = IMG_TWO
        )
    )
}