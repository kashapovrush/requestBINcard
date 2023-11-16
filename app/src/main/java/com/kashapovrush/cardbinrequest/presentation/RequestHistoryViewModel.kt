package com.kashapovrush.cardbinrequest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kashapovrush.cardbinrequest.domain.GetCardListUseCase
import javax.inject.Inject

class RequestHistoryViewModel @Inject constructor(
    private val getCardListUseCase: GetCardListUseCase
) : ViewModel() {

    val cardList = getCardListUseCase().asLiveData()

}