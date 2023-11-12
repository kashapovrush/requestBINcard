package com.kashapovrush.cardbinrequest.data.repository

import com.kashapovrush.cardbinrequest.data.network.ApiService
import com.kashapovrush.cardbinrequest.domain.CardBINRepository
import com.kashapovrush.cardbinrequest.domain.model.CardInfoMain
import retrofit2.Call
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): CardBINRepository {


    override fun getCardInfo(number: String): Call<CardInfoMain> {
        return apiService.getCardInfo(number)
    }
}
