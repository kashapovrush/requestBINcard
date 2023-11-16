package com.kashapovrush.cardbinrequest.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kashapovrush.cardbinrequest.domain.GetCardItemUseCase
import com.kashapovrush.cardbinrequest.domain.model.CardInfoMain
import kotlinx.coroutines.launch
import javax.inject.Inject

class CardInfoViewModel @Inject constructor(
    private val getCardItemUseCase: GetCardItemUseCase
): ViewModel() {

    private val _showCardItem = MutableLiveData<CardInfoMain?>()
    val showCardItem: LiveData<CardInfoMain?>
        get() = _showCardItem

    fun getCardItem(id: String) {
        viewModelScope.launch {
            _showCardItem.postValue(getCardItemUseCase(id))
        }

    }

}