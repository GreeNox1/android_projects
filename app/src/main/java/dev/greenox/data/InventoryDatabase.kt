package dev.greenox.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao
    companion object {
        @Volatile
        private var instance: InventoryDatabase? = null

        fun getDatabase(context: Context): InventoryDatabase {
            return instance ?: synchronized(lock = this) {
                Room.databaseBuilder(
                    context,
                    klass = InventoryDatabase::class.java,
                    name = "item_database",
                )
                    .fallbackToDestructiveMigration(dropAllTables = true)
                    .build()
                    .also { instance = it }
            }
        }
    }
}