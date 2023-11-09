package com.kashapovrush.cardbinrequest.domain

data class CardInfo(
    val number: NumberCard,
    val scheme: String,
    val type: String,
    val brand: String,
    val prepaid: Boolean,
    val country: CountryInfo,
    val bank: BankInfo
)
