package com.example.exchangerates

import java.io.Serializable

data class ExchangeDto(
    val name_type: String,
    val name: String,
    val street_type: String,
    val street: String,
    val home_number: String
): Serializable
