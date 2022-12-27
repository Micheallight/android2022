package com.testapp.phonerecycle

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Product::class], version = 1)
@TypeConverters(Converters::class)
abstract class ProductDB : RoomDatabase() {
    abstract fun productDao(): ProductDao
}