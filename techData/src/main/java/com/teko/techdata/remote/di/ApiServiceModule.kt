package com.teko.techdata.remote.di

import com.teko.techdata.remote.TechApiServices
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
interface ApiServiceModule {

    @Binds
    @Provides
    fun providesTechApiServices(retrofit: Retrofit): TechApiServices =
        retrofit.create(TechApiServices::class.java)
}
