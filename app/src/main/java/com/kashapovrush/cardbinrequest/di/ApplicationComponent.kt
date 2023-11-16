package com.kashapovrush.cardbinrequest.di

import android.app.Application
import com.kashapovrush.cardbinrequest.presentation.CardInfoApplication
import com.kashapovrush.cardbinrequest.presentation.MainActivity
import com.kashapovrush.cardbinrequest.presentation.RequestHistoryActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(app: CardInfoApplication)

    fun inject (activity: MainActivity)

    fun inject (activity: RequestHistoryActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }

}