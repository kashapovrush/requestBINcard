package com.kashapovrush.cardbinrequest.domain

import android.content.Context
import javax.inject.Inject

class IntentGoToSiteUseCase @Inject constructor(private val repository: CardBINRepository) {

    operator fun invoke (url: String, context: Context) {
        repository.intentGoToSite(url, context)
    }
}