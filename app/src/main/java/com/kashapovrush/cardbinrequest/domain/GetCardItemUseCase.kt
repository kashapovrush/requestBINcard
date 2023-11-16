package com.kashapovrush.cardbinrequest.domain

import com.kashapovrush.cardbinrequest.domain.model.CardInfoMain
import javax.inject.Inject

class GetCardItemUseCase@Inject constructor(private val repository: CardBINRepository) {

    suspend operator fun invoke (id: String): CardInfoMain {
        return repository.getCardItem(id)
    }
}