package com.kashapovrush.cardbinrequest.domain

import com.kashapovrush.cardbinrequest.domain.model.CardInfoMain
import javax.inject.Inject

class AddCardInfoItemUseCase @Inject constructor(private val repository: CardBINRepository) {

    suspend operator fun invoke (cardInfoMain: CardInfoMain) {
        repository.addCardInfoItem(cardInfoMain)
    }
}