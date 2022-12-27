package com.michaellight.exchangeratesv1

data class Rate(
    val Cur_ID: Int,
    val Date: String,
    val Cur_Abbreviation: String,
    val Cur_Scale: Int,
    val Cur_Name: String,
    val Cur_OfficialRate: Double
)