package com.teko.techdata.local.di

import com.teko.commondata.local.DatabaseHelper
import com.teko.techdata.local.TechDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun providesAppDatabase(helper: DatabaseHelper): TechDatabase {
        return TechDatabase.createInstance(helper)
    }
}
