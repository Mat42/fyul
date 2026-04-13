package com.fuyl.challenge.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.SharingStarted
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ViewModelSharingStarted

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelSharingStarted
    fun provideSharingStarted(): SharingStarted = SharingStarted.WhileSubscribed(5_000)
}
