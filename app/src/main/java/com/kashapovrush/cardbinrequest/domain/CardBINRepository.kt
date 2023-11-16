package com.kashapovrush.cardbinrequest.domain

import android.content.Context
import com.kashapovrush.cardbinrequest.domain.model.CardInfoMain
import kotlinx.coroutines.flow.Flow
import retrofit2.Call

interface CardBINRepository {

    fun getCardInfo(number: String): Call<CardInfoMain>

    fun intentToCall(phoneNumber: String, context: Context)

    fun intentGoToMap(latitude: String, longitude: String, context: Context)

    fun intentGoToSite(url: String, context: Context)

    fun getCardInfoList(): Flow<List<CardInfoMain>>

    suspend fun addCardInfoItem(cardInfoMain: CardInfoMain)

    suspend fun deleteCardInfo( cardInfoMain: CardInfoMain)
}