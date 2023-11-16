package com.kashapovrush.cardbinrequest.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
data class CardInfoDb(
//    val number: NumberCard
    val length: Int?,
    val luhn: Boolean?,

    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,


//    val country: CountryInfo,
    val numeric: String?,
    val alpha2: String?,
    val nameCountry: String?,
    val emoji: String?,
    val currency: String?,
    val latitude: Int?,
    val longitude: Int?,

//    val bank: BankInfo
    val nameBank: String?,
    val url: String?,
    val phone: String?,
    val city: String?,

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val inputNumber: String
)
