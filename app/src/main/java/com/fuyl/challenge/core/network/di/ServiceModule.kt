package com.fuyl.challenge.core.network.di

import com.fuyl.challenge.core.network.api.DummyProductApi
import com.fuyl.challenge.core.network.service.DummyApiService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {

    @Binds
    @Singleton
    abstract fun bindDummyProductApi(
        dummyApiService: DummyApiService
    ): DummyProductApi
}