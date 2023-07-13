package com.teko.techdata.repository

import com.teko.techdata.repository.di.AuthRepository
import com.teko.techdata.repository.di.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun providesAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository
}
