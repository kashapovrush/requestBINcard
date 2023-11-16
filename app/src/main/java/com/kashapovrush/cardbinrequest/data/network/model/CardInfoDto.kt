package com.kashapovrush.cardbinrequest.data.network.model

data class CardInfoDto(
    var number: NumberCardDto? = null,
    var scheme: String? = null,
    var type: String? = null,
    var brand: String? = null,
    var prepaid: Boolean? = null,
    var country: CountryInfoDto? = null,
    var bank: BankInfoDto? = null,
    val inputNumber: String,
    var id: Int = 0
)
