package com.teko.local.di

import com.teko.local.AppDatabase
import com.teko.local.utils.DatabaseHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun providesAppDatabase(helper: DatabaseHelper): AppDatabase {
        return AppDatabase.createInstance(helper)
    }
}
