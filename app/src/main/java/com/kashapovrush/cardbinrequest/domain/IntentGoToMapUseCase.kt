package com.kashapovrush.cardbinrequest.domain

import android.content.Context
import javax.inject.Inject

class IntentGoToMapUseCase @Inject constructor(private val repository: CardBINRepository) {

    operator fun invoke (latitude: String, longitude: String, context: Context) {
        repository.intentGoToMap(latitude, longitude, context)
    }
}