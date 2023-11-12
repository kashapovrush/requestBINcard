package com.kashapovrush.cardbinrequest.di

import androidx.lifecycle.ViewModel
import com.kashapovrush.cardbinrequest.presentation.MainViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}