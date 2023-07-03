package com.teko.network.di

import com.teko.network.features.auth.AuthRemoteSource
import com.teko.network.features.auth.AuthRemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import javax.inject.Singleton

@Module
@InstallIn
interface RemoteSourceModule {

    @Binds
    @Singleton
    fun providesAuthRemoteSource(
        authRemoteSource: AuthRemoteSourceImpl
    ): AuthRemoteSource
}
