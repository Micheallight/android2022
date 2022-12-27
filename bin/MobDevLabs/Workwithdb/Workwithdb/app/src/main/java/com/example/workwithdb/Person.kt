package com.example.workwithdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="persons")
data class Person(
    @PrimaryKey val name: String,
    val secondName: String,
    val age: Int
)