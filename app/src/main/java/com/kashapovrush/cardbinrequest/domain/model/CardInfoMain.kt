package com.kashapovrush.cardbinrequest.domain.model

data class CardInfoMain(
    val number: NumberCard,
    val scheme: String,
    val type: String,
    val brand: String,
    val prepaid: Boolean,
    val country: CountryInfo,
    val bank: BankInfo
)
