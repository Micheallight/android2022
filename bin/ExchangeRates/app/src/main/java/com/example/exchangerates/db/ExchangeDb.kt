package com.example.exchangerates.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.exchangerates.data.Exchange

@Database(entities = [Exchange::class], version = 1)
abstract class ExchangeDb: RoomDatabase() {

    abstract fun exchangeDao(): ExchangeDao

    companion object {
        private var INSTANCE: ExchangeDb? = null

        fun getDatabase(context: Context): ExchangeDb {
            return INSTANCE ?: synchronized(this) {
                val instance =
                    Room.databaseBuilder(context, ExchangeDb::class.java, "person_db")
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}