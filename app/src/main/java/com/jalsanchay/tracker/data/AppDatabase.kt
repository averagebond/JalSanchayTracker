package com.jalsanchay.tracker.data

import android.content.Context
import androidx.room.*

@Database(entities = [RainfallEntry::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun rainfallDao(): RainfallDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "jal_sanchay_db")
                    .build().also { INSTANCE = it }
            }
    }
}
