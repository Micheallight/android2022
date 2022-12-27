package com.michaellight.exchangeratesv1

data class Data(
    val country_id: Int,
    val current_season_id: Int,
    val founded: Int,
    val id: Int,
    val is_placeholder: Boolean,
    val legacy_id: Int,
    val logo_path: String,
    val name: String,
    val national_team: Boolean,
    val short_code: String,
    val twitter: Any,
    val venue_id: Int
)