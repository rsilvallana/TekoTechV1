package com.teko.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.teko.local.features.token.dao.TokenDao
import com.teko.local.features.token.models.AccessTokenDB
import com.teko.local.features.user.dao.UserDao
import com.teko.local.features.user.model.UserDB
import com.teko.local.features.user.model.UserDBConverters
import com.teko.local.utils.DatabaseHelper

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
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun tokenDao(): TokenDao

    companion object {

        // We handle singleton implementation here via dagger, instead of manually handling it.
        fun createInstance(helper: DatabaseHelper): AppDatabase = buildDatabase(helper)

        private fun buildDatabase(helper: DatabaseHelper): AppDatabase {
            val dbName = "tekotech.db"

            return helper.createDatabase(
                name = dbName,
                kClass = AppDatabase::class
            )
        }
    }
}
