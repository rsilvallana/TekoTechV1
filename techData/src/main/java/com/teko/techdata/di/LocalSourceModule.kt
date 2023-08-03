package com.teko.techdata.di

import android.content.SharedPreferences
import com.teko.techdata.local.TechDatabase
import com.teko.techdata.local.features.auth.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalSourceModule {

    @Provides
    @Singleton
    fun providesUserLocalSource(
        techDatabase: TechDatabase
    ): UserLocalSource {
        return UserLocalSourceImpl(techDatabase.userDao())
    }

    @Provides
    @Singleton
    fun providesAccessTokenLocalSource(
        sharedPreferences: SharedPreferences,
        techDatabase: TechDatabase
    ): AccessTokenLocalSource {
        return AccessTokenLocalSourceImpl(sharedPreferences, techDatabase.tokenDao())
    }

    @Provides
    @Singleton
    fun providesSessionLocalSource(
        userLocalSource: UserLocalSource,
        accessTokenLocalSource: AccessTokenLocalSource,
        sharedPreferences: SharedPreferences
    ): SessionLocalSource {
        return SessionLocalSourceImpl(
            userLocalSource,
            accessTokenLocalSource,
            sharedPreferences
        )
    }
}
