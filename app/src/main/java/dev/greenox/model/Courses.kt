package dev.greenox.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Courses (
    @DrawableRes val image: Int,
    @StringRes val name: Int,
    val size: Int
)