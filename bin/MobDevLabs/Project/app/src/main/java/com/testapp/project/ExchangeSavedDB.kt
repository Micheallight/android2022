package com.testapp.project

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Exchange::class], version = 1)
abstract class ExchangeSavedDB : RoomDatabase() {
    abstract fun exchangeSavedDao() : ExchangeSavedDao
}