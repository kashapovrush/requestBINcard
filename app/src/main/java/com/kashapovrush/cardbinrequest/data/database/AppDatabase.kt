package com.kashapovrush.cardbinrequest.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CardInfoDb::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun cardInfoDao(): CardInfoDao

    companion object {

        private var db: AppDatabase? = null
        private const val DB_NAME = "card.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DB_NAME)
                    .build()
                db = instance
                return instance
            }
        }
    }
}