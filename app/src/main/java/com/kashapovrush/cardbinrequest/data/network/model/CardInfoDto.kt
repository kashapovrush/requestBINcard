package com.kashapovrush.cardbinrequest.data.network.model

data class CardInfoDto(
    val number: NumberCardDto,
    val scheme: String,
    val type: String,
    val brand: String,
    val prepaid: Boolean,
    val country: CountryInfoDto,
    val bank: BankInfoDto
)
