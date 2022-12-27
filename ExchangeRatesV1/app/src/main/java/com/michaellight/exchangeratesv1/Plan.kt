package com.michaellight.exchangeratesv1

data class Plan(
    val features: String,
    val name: String,
    val price_monthly: String,
    val price_yearly: String,
    val request_limit: String,
    val sport: String
)