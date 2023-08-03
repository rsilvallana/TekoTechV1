package com.teko.techdata.di

import com.teko.techdata.remote.TechApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    @Provides
    @Singleton
    fun providesTechApiServices(retrofit: Retrofit): TechApiServices =
        retrofit.create(TechApiServices::class.java)
}
