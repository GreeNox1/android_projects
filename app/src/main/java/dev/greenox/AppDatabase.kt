package dev.greenox

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Email::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun emailDao(): EmailDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context
        ): AppDatabase {
            return INSTANCE ?: synchronized(lock = this) {
                val instance = Room.databaseBuilder(
                    context,
                    klass = AppDatabase::class.java,
                    name = "app_database"
                )
                    .createFromAsset(databaseFilePath = "database/Email.db")
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}
