package com.kashapovrush.cardbinrequest.domain

import androidx.lifecycle.LiveData
import com.kashapovrush.cardbinrequest.domain.model.CardInfoMain
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback

interface CardBINRepository {

    fun getCardInfo(number: String): Call<CardInfoMain>
}