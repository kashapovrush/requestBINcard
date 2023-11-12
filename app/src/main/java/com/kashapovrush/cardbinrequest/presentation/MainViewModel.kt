package com.kashapovrush.cardbinrequest.presentation

import androidx.lifecycle.ViewModel
import com.kashapovrush.cardbinrequest.domain.GetCardInfoUseCase
import com.kashapovrush.cardbinrequest.domain.model.CardInfoMain
import retrofit2.Callback
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getCardInfoUseCase: GetCardInfoUseCase
) : ViewModel() {

    fun getCardInfo(number: String, callback: Callback<CardInfoMain>) {
        return getCardInfoUseCase(number).enqueue(callback)
    }
}