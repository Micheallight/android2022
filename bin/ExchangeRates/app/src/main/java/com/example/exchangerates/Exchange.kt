package com.example.exchangerates

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exchanges", primaryKeys = ["name_type","name","street_type","street","home_number"])
data class Exchange(
   /* @PrimaryKey
    val id: Int,*/
    val USD_in: String,
    val USD_out: String,
    val name_type: String,
    val name: String,
    val street_type: String,
    val street: String,
    val home_number: String
)
