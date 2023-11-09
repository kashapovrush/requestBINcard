package com.kashapovrush.cardbinrequest.domain

import androidx.lifecycle.LiveData
import com.kashapovrush.cardbinrequest.domain.model.CardInfo

interface CardBINRepository {

    fun getCardInfo(number: String): LiveData<CardInfo>
}