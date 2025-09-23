package dev.greenox.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val price: Double,
    val quantity: Int
)