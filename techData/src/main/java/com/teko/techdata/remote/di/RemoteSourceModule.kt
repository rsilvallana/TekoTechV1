package com.teko.techdata.remote.di

import com.teko.techdata.remote.features.auth.AuthRemoteSource
import com.teko.techdata.remote.features.auth.AuthRemoteSourceImpl
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
