package dev.greenox.amphibians.fake

import dev.greenox.model.AmphibiansModel

object FakeDataSource {
    const val ID_ONE = "Image 1"
    const val ID_TWO = "Image 2"
    const val DESCRIPTION_ONE = "Description 1"
    const val DESCRIPTION_TWO = "Description 2"
    const val TYPE_ONE = "Type 1"
    const val TYPE_TWO = "Type 2"
    const val IMG_ONE = "http://image_1"
    const val IMG_TWO = "http://image_2"

    val amphibiansList = listOf(
        AmphibiansModel(
            name = ID_ONE,
            description = DESCRIPTION_ONE,
            type = TYPE_ONE,
            imgUrl = IMG_ONE,
        ),
        AmphibiansModel(
            name = ID_TWO,
            description = DESCRIPTION_TWO,
            type = TYPE_TWO,
            imgUrl = IMG_TWO,
        ),
    )
}