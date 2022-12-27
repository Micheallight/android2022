package com.example.exchangerates.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "exchanges")
data class Exchange(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val USD_in: String,
    val USD_out: String,
    val name_type: String,
    val name: String,
    val street_type: String,
    val street: String,
    val home_number: String
) : Serializable
