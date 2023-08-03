package com.teko.techdata.di

import com.teko.techdata.remote.features.auth.AuthRemoteSource
import com.teko.techdata.repository.features.auth.AuthRepository
import com.teko.techdata.repository.features.auth.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesAuthRepository(
        authRemoteSource: AuthRemoteSource
    ): AuthRepository = AuthRepositoryImpl(authRemoteSource)
}
