package com.kashapovrush.cardbinrequest.data.network.model

data class CountryInfoDto(
    val numeric: String?,
    val alpha2: String?,
    val name: String?,
    val emoji: String?,
    val currency: String?,
    val latitude: Int?,
    val longitude: Int?,
)
