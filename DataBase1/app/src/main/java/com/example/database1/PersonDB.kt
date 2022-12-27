package com.example.database1

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Person::class], version = 1)
abstract class PersonDB : RoomDatabase() {
	abstract fun personDao(): PersonDao
}