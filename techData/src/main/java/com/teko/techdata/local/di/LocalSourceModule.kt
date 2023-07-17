package com.teko.techdata.local.di

import android.content.SharedPreferences
import com.teko.techdata.local.TechDatabase
import com.teko.techdata.local.features.auth.SessionLocalSource
import com.teko.techdata.local.features.auth.SessionLocalSourceImpl
import com.teko.techdata.local.features.auth.AccessTokenLocalSource
import com.teko.techdata.local.features.auth.AccessTokenLocalSourceImpl
import com.teko.techdata.local.features.auth.UserLocalSource
import com.teko.techdata.local.features.auth.UserLocalSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface LocalSourceModule {

    @Binds
    @Singleton
    fun providesUserLocalSource(
        techDatabase: TechDatabase
    ): UserLocalSource {
        return UserLocalSourceImpl(techDatabase.userDao())
    }

    @Binds
    @Singleton
    fun providesAccessTokenLocalSource(
        sharedPreferences: SharedPreferences,
        techDatabase: TechDatabase
    ): AccessTokenLocalSource {
        return AccessTokenLocalSourceImpl(sharedPreferences, techDatabase.tokenDao())
    }

    @Binds
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
