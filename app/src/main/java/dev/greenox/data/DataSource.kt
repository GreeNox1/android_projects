package dev.greenox.data

import dev.greenox.R
import dev.greenox.model.Dessert

object DataSource {
    val desserts = listOf(
        Dessert(imageId = R.drawable.cupcake, price = 5, startProductionAmount = 0),
        Dessert(imageId = R.drawable.donut, price = 10, startProductionAmount = 5),
        Dessert(imageId = R.drawable.eclair, price = 15, startProductionAmount = 20),
        Dessert(imageId = R.drawable.froyo, price = 30, startProductionAmount = 50),
        Dessert(imageId = R.drawable.gingerbread, price = 50, startProductionAmount = 100),
        Dessert(imageId = R.drawable.honeycomb, price = 100, startProductionAmount = 200),
        Dessert(imageId = R.drawable.icecreamsandwich, price = 500, startProductionAmount = 500),
        Dessert(imageId = R.drawable.jellybean, price = 1000, startProductionAmount = 1000),
        Dessert(imageId = R.drawable.kitkat, price = 2000, startProductionAmount = 2000),
        Dessert(imageId = R.drawable.lollipop, price = 3000, startProductionAmount = 4000),
        Dessert(imageId = R.drawable.marshmallow, price = 4000, startProductionAmount = 8000),
        Dessert(imageId = R.drawable.nougat, price = 5000, startProductionAmount = 16000),
        Dessert(imageId = R.drawable.oreo, price = 6000, startProductionAmount = 20000)
    )
}