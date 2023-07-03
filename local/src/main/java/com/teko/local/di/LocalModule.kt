package com.teko.local.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.teko.local.AppDatabase
import com.teko.local.BuildConfig
import com.teko.local.features.session.SessionLocalSource
import com.teko.local.features.session.SessionLocalSourceImpl
import com.teko.local.features.token.AccessTokenLocalSource
import com.teko.local.features.token.AccessTokenLocalSourceImpl
import com.teko.local.features.user.UserLocalSource
import com.teko.local.features.user.UserLocalSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton

@Module
@InstallIn
interface LocalModule {

    @Provides
    @Singleton
    fun providesSharedPreferences(application: Application): SharedPreferences {
        return if (BuildConfig.DEBUG) {
            application.getSharedPreferences(
                getDefaultPreferencesName(application),
                Context.MODE_PRIVATE
            )
        } else {
            val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
            EncryptedSharedPreferences
                .create(
                    getDefaultPreferencesName(application),
                    masterKeyAlias,
                    application,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                )
        }
    }

    private fun getDefaultPreferencesName(application: Application): String {
        return application.packageName + "_prefs"
    }

    @Binds
    @Singleton
    fun providesUserLocalSource(
        appDatabase: AppDatabase
    ): UserLocalSource {
        return UserLocalSourceImpl(appDatabase.userDao())
    }

    @Binds
    @Singleton
    fun providesAccessTokenLocalSource(
        sharedPreferences: SharedPreferences,
        appDatabase: AppDatabase
    ): AccessTokenLocalSource {
        return AccessTokenLocalSourceImpl(sharedPreferences, appDatabase.tokenDao())
    }

    @Binds
    @Singleton
    fun providesSessionLocalSource(
        userLocalSource: UserLocalSource,
        accessTokenLocalSource: AccessTokenLocalSource,
        sharedPreferences: SharedPreferences
    ): SessionLocalSource {
        return SessionLocalSourceImpl(userLocalSource, accessTokenLocalSource, sharedPreferences)
    }
}
