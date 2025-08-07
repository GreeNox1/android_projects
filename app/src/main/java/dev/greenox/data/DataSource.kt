package dev.greenox.data

import dev.greenox.R

object DataSource {
    val flavors = listOf(
        R.string.vanilla,
        R.string.chocolate,
        R.string.red_velvet,
        R.string.salted_caramel,
        R.string.coffee
    )

    val quantityOptions = listOf(
        Pair(first = R.string.one_cupcake, second = 1),
        Pair(first = R.string.six_cupcakes, second = 6),
        Pair(first = R.string.twelve_cupcakes, second =  12)
    )
}