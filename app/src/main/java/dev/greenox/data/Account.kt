package dev.greenox.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Account(
    val id: Long,
    @field:StringRes val firstName: Int,
    @field:StringRes val lastName: Int,
    @field:StringRes val email: Int,
    @field:DrawableRes val avatar: Int
)