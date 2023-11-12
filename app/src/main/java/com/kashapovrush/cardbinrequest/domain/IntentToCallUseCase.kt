package com.kashapovrush.cardbinrequest.domain

import android.content.Context
import javax.inject.Inject

class IntentToCallUseCase @Inject constructor(private val repository: CardBINRepository) {

    operator fun invoke (phoneNumber: String, context: Context) {
        repository.intentToCall(phoneNumber, context)
    }
}