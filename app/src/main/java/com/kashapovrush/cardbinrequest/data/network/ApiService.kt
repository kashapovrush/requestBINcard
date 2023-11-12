package com.kashapovrush.cardbinrequest.data.network

import com.kashapovrush.cardbinrequest.domain.model.CardInfoMain
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("{number}")
    fun getCardInfo(@Path("number") number: String): Call<CardInfoMain>
}