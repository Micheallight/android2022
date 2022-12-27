package com.michaellight.wishlistv1

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Lang::class], version = 1)
@TypeConverters(Converters::class)
abstract class LangDB : RoomDatabase() {
    abstract fun productDao(): LangDAO
}