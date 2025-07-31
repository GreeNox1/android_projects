package dev.greenox.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Wellness(
    @StringRes val name: Int,
    @StringRes val description: Int,
    @DrawableRes val image: Int,
)