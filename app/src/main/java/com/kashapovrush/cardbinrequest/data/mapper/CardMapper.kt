package com.kashapovrush.cardbinrequest.data.mapper

import com.kashapovrush.cardbinrequest.data.database.CardInfoDb
import com.kashapovrush.cardbinrequest.domain.model.BankInfo
import com.kashapovrush.cardbinrequest.domain.model.CardInfoMain
import com.kashapovrush.cardbinrequest.domain.model.CountryInfo
import com.kashapovrush.cardbinrequest.domain.model.NumberCard
import javax.inject.Inject

class CardMapper @Inject constructor() {

    fun mapEntityToDbModel(cardInfo: CardInfoMain): CardInfoDb {
        return CardInfoDb(
            length = cardInfo.number?.length,
            luhn = cardInfo.number?.luhn,
            scheme = cardInfo.scheme,
            type = cardInfo.type,
            brand = cardInfo.brand,
            prepaid = cardInfo.prepaid,
            numeric = cardInfo.country?.numeric,
            alpha2 = cardInfo.country?.alpha2,
            nameCountry = cardInfo.country?.name,
            currency = cardInfo.country?.currency,
            longitude = cardInfo.country?.longitude,
            latitude = cardInfo.country?.latitude,
            emoji = cardInfo.country?.emoji,
            nameBank = cardInfo.bank?.name,
            city = cardInfo.bank?.city,
            url = cardInfo.bank?.url,
            phone = cardInfo.bank?.phone,
            id = cardInfo.id,
            inputNumber = cardInfo.inputNumber
        )
    }

    fun mapDbModelToEntity(cardInfoDb: CardInfoDb): CardInfoMain {
        return CardInfoMain(
            scheme = cardInfoDb.scheme,
            type = cardInfoDb.type,
            prepaid = cardInfoDb.prepaid,
            brand = cardInfoDb.brand,
            number = cardInfoDbToNumberCard(cardInfoDb),
            country = cardInfoDbToCountryInfo(cardInfoDb),
            bank = cardInfoDbToBankInfo(cardInfoDb),
            id = cardInfoDb.id,
            inputNumber = cardInfoDb.inputNumber
        )
    }

    fun mapListDbModelToListEntity(list: List<CardInfoDb>): List<CardInfoMain> {
        return list.map {
            mapDbModelToEntity(it)
        }
    }

    fun cardInfoDbToNumberCard(db: CardInfoDb): NumberCard {
        return NumberCard(
            length = db.length,
            luhn = db.luhn
        )
    }

    fun cardInfoDbToBankInfo(db: CardInfoDb): BankInfo {
        return BankInfo(
            name = db.nameBank,
            url = db.url,
            phone = db.phone,
            city = db.city
        )
    }

    fun cardInfoDbToCountryInfo(db: CardInfoDb): CountryInfo {
        return CountryInfo(
            numeric = db.numeric,
            alpha2 = db.alpha2,
            emoji = db.emoji,
            currency = db.currency,
            latitude = db.latitude,
            longitude = db.longitude,
            name = db.nameCountry
        )
    }
}