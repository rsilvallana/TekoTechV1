package com.teko.techdata.di

import com.teko.techdata.remote.TechApiServices
import com.teko.techdata.remote.features.auth.AuthRemoteSource
import com.teko.techdata.remote.features.auth.AuthRemoteSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteSourceModule {

    @Provides
    @Singleton
    fun providesAuthRemoteSource(
        techApiServices: TechApiServices
    ): AuthRemoteSource = AuthRemoteSourceImpl(techApiServices)
}
