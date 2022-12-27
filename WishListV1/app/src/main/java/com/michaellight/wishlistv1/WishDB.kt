package com.michaellight.wishlistv1

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Wish::class], version = 1)
@TypeConverters(Converters::class)
abstract class WishDB : RoomDatabase() {
    abstract fun productDao(): WishDAO
}