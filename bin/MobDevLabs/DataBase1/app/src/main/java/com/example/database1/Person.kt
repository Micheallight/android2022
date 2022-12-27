package com.example.database1

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persons")
data class Person(
	@PrimaryKey val name: String, // переделать, чтобы idшник был нормальный
	val secondName: String,
	val age: Int
)