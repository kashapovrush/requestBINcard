package com.kashapovrush.cardbinrequest.domain

import androidx.lifecycle.LiveData
import com.kashapovrush.cardbinrequest.domain.model.CardInfo

class GetCardInfoUseCase(private val repository: CardBINRepository) {

    operator fun invoke(number: String): LiveData<CardInfo> {
        return repository.getCardInfo(number)
    }
}