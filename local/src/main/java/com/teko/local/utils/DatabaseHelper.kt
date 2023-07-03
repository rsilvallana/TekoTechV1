package com.teko.local.utils

import android.app.Application
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.security.crypto.EncryptedSharedPreferences
import com.teko.local.BuildConfig
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.util.Base64
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.reflect.KClass
import net.sqlcipher.database.SupportFactory

@Suppress("MemberVisibilityCanBePrivate")
@Singleton
class DatabaseHelper @Inject constructor(
    private val app: Application,
    private val sharedPreferences: SharedPreferences
) {

    private val random by lazy {
        try {
            SecureRandom.getInstanceStrong()
        } catch (e: NoSuchAlgorithmException) {
            SecureRandom()
        }
    }

    fun <T : RoomDatabase> createDatabase(
        name: String,
        kClass: KClass<T>,
        encrypted: Boolean = !BuildConfig.DEBUG,
        builder: RoomDatabase.Builder<T>.() -> Unit = {}
    ): T {
        require(name.isNotBlank()) {
            "Database name should not be blank"
        }

        val dbBuilder = Room.databaseBuilder(app.applicationContext, kClass.java, name)
            .fallbackToDestructiveMigration()
            .apply(builder)

        if (encrypted) {
            val passphrase = getOrCreateDatabasePassphrase(name)
            dbBuilder.openHelperFactory(SupportFactory(passphrase))
        }

        return dbBuilder.build()
    }

    private fun generateRandomKey(size: Int = 32): ByteArray = ByteArray(size).apply {
        random.nextBytes(this)
    }

    private fun getOrCreateDatabasePassphrase(name: String): ByteArray {
        check(sharedPreferences is EncryptedSharedPreferences) {
            "SharedPreferences is not encrypted!"
        }

        val dbPassphraseKey = "_$$name-dbKey"

        fun tryGetKey(): String? = sharedPreferences.getString(dbPassphraseKey, null)

        val tryDbKey = tryGetKey()

        val decoder = Base64.getDecoder()
        val encoder = Base64.getEncoder()

        return if (tryDbKey != null) {
            decoder.decode(tryDbKey)
        } else {
            val newDbPassphrase = generateRandomKey()

            sharedPreferences.edit {
                putString(dbPassphraseKey, encoder.encodeToString(newDbPassphrase))
            }

            // We fetch again from shared prefs rather than using the variable
            // to make sure it's available and also for single source of truth
            tryGetKey()?.let {
                decoder.decode(it)
            } ?: throw IllegalStateException("Cannot create database key")
        }
    }
}
