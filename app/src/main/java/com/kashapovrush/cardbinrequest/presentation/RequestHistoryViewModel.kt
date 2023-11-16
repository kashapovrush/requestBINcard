package com.kashapovrush.cardbinrequest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kashapovrush.cardbinrequest.domain.DeleteCardInfoUseCase
import com.kashapovrush.cardbinrequest.domain.GetCardListUseCase
import com.kashapovrush.cardbinrequest.domain.model.CardInfoMain
import kotlinx.coroutines.launch
import javax.inject.Inject

class RequestHistoryViewModel @Inject constructor(
    private val getCardListUseCase: GetCardListUseCase,
    private val deleteCardInfoUseCase: DeleteCardInfoUseCase
) : ViewModel() {

    val cardList = getCardListUseCase().asLiveData()

    fun deleteCardInfo( cardInfoMain: CardInfoMain) {
        viewModelScope.launch {
            deleteCardInfoUseCase(cardInfoMain)
        }
    }

}