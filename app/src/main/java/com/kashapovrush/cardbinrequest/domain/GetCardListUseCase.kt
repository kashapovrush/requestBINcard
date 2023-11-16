package com.kashapovrush.cardbinrequest.domain

import com.kashapovrush.cardbinrequest.domain.model.CardInfoMain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCardListUseCase @Inject constructor(private val repository: CardBINRepository) {

    operator fun invoke(): Flow<List<CardInfoMain>> {
        return repository.getCardInfoList()
    }
}