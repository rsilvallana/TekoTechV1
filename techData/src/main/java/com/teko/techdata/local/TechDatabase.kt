package com.teko.techdata.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.teko.commondata.local.DatabaseHelper
import com.teko.techdata.local.features.auth.dao.TokenDao
import com.teko.techdata.local.features.auth.domain.AccessTokenDB
import com.teko.techdata.local.features.auth.dao.UserDao
import com.teko.techdata.local.features.auth.domain.UserDB
import com.teko.techdata.local.features.auth.domain.UserDBConverters

@Database(
    entities = [
        UserDB::class,
        AccessTokenDB::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(
    UserDBConverters::class
)
abstract class TechDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun tokenDao(): TokenDao

    companion object {

        // We handle singleton implementation here via dagger, instead of manually handling it.
        fun createInstance(helper: DatabaseHelper): TechDatabase = buildDatabase(helper)

        private fun buildDatabase(helper: DatabaseHelper): TechDatabase {
            val dbName = "tech.db"

            return helper.createDatabase(
                name = dbName,
                kClass = TechDatabase::class
            )
        }
    }
}
