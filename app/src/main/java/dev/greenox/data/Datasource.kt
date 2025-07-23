package dev.greenox.data

import dev.greenox.R
import dev.greenox.model.Affirmation

class Datasource {
    fun loadAffirmation(): List<Affirmation> {
        return listOf(
            Affirmation(
                stringResourcesId = R.string.affirmation1,
                imageResourcesId = R.drawable.image1
            ),
            Affirmation(
                stringResourcesId = R.string.affirmation2,
                imageResourcesId = R.drawable.image2
            ),
            Affirmation(
                stringResourcesId = R.string.affirmation3,
                imageResourcesId = R.drawable.image3
            ),
            Affirmation(
                stringResourcesId = R.string.affirmation4,
                imageResourcesId = R.drawable.image4
            ),
            Affirmation(
                stringResourcesId = R.string.affirmation5,
                imageResourcesId = R.drawable.image5
            ),
            Affirmation(
                stringResourcesId = R.string.affirmation6,
                imageResourcesId = R.drawable.image6
            ),
            Affirmation(
                stringResourcesId = R.string.affirmation7,
                imageResourcesId = R.drawable.image7
            ),
            Affirmation(
                stringResourcesId = R.string.affirmation8,
                imageResourcesId = R.drawable.image8
            ),
            Affirmation(
                stringResourcesId = R.string.affirmation9,
                imageResourcesId = R.drawable.image9
            ),
            Affirmation(
                stringResourcesId = R.string.affirmation10,
                imageResourcesId = R.drawable.image10
            ),
        )
    }
}