package com.kashapovrush.cardbinrequest.di

import com.kashapovrush.cardbinrequest.data.network.ApiFactory
import com.kashapovrush.cardbinrequest.data.network.ApiService
import com.kashapovrush.cardbinrequest.data.repository.CardRepositoryImpl
import com.kashapovrush.cardbinrequest.domain.CardBINRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindsCardRepository(impl: CardRepositoryImpl): CardBINRepository

    companion object {

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}