package com.kashapovrush.cardbinrequest.domain.model

data class CardInfoMain(
    var number: NumberCard? = null,
    var scheme: String? = null,
    var type: String? = null,
    var brand: String? = null,
    var prepaid: Boolean? = null,
    var country: CountryInfo? = null,
    var bank: BankInfo? = null,
    val inputNumber: String,
    var id: Int = 0
)
