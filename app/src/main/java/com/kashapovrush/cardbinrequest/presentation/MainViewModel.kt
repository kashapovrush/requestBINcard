package com.kashapovrush.cardbinrequest.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kashapovrush.cardbinrequest.domain.AddCardInfoItemUseCase
import com.kashapovrush.cardbinrequest.domain.GetCardInfoUseCase
import com.kashapovrush.cardbinrequest.domain.IntentGoToMapUseCase
import com.kashapovrush.cardbinrequest.domain.IntentGoToSiteUseCase
import com.kashapovrush.cardbinrequest.domain.IntentToCallUseCase
import com.kashapovrush.cardbinrequest.domain.model.CardInfoMain
import kotlinx.coroutines.launch
import retrofit2.Callback
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getCardInfoUseCase: GetCardInfoUseCase,
    private val intentToCallUseCase: IntentToCallUseCase,
    private val intentGoToMapUseCase: IntentGoToMapUseCase,
    private val intentGoToSiteUseCase: IntentGoToSiteUseCase,
    private val addCardInfoItemUseCase: AddCardInfoItemUseCase
) : ViewModel() {

    fun getCardInfo(number: String, callback: Callback<CardInfoMain>) {
        return getCardInfoUseCase(number).enqueue(callback)
    }

    fun addCardInfoItem(cardInfoMain: CardInfoMain) {
        viewModelScope.launch {
            addCardInfoItemUseCase(cardInfoMain)
        }

    }

    fun intentToCall(phoneNumber: String, context: Context) {
        intentToCallUseCase(phoneNumber, context)
    }

    fun intentGoToMap(latitude: String, longitude: String, context: Context) {
        intentGoToMapUseCase(latitude, longitude, context)
    }

    fun intentGOToSite(url: String, context: Context) {
        intentGoToSiteUseCase(url, context)
    }
}