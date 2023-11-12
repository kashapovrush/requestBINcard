package com.kashapovrush.cardbinrequest.presentation

import android.app.Application
import com.kashapovrush.cardbinrequest.di.DaggerApplicationComponent

class CardInfoApplication: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}