package com.kashapovrush.cardbinrequest.domain

data class CountryInfo(
    val numeric: String,
    val alpha2: String,
    val name: String,
    val emoji: String,
    val currency: String,
    val latitude: Int,
    val longitude: Int,
)
