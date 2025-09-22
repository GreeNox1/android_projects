package dev.greenox

import androidx.room.Dao
import androidx.room.Query

@Dao
interface EmailDao {
    @Query(value = "SELECT * FROM email")
    suspend fun getAll(): List<Email>
}