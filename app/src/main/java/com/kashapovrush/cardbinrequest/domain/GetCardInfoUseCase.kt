package com.kashapovrush.cardbinrequest.domain

import androidx.lifecycle.LiveData
import com.kashapovrush.cardbinrequest.domain.model.CardInfoMain
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import javax.inject.Inject

class GetCardInfoUseCase @Inject constructor(private val repository: CardBINRepository) {

    operator fun invoke(number: String): Call<CardInfoMain> {
        return repository.getCardInfo(number)
    }
}