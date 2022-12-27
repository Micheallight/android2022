package com.michaellight.bankapiv1

import androidx.room.Entity

@Entity(tableName = "exchanges", primaryKeys = ["name_type","name","street_type","street","home_number"])
data class Exchange(
	val USD_in: String,
	val USD_out: String,
	val name_type: String,
	val name: String,
	val street_type: String,
	val street: String,
	val home_number: String
)