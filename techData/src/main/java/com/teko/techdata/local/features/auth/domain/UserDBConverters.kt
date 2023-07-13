package com.teko.techdata.local.features.auth.domain

import androidx.room.TypeConverter
import com.google.gson.Gson

class UserDBConverters {

    @TypeConverter
    fun detailsFromString(value: String?): UserDB.DetailsDB {
        return Gson().fromJson(value ?: "{}", UserDB.DetailsDB::class.java)
    }

    @TypeConverter
    fun detailsToString(details: UserDB.DetailsDB?): String {
        return Gson().toJson(details ?: UserDB.DetailsDB.empty())
    }

    @TypeConverter
    fun assistantToolsFromString(value: String?): UserDB.AssistantToolsDB {
        return Gson().fromJson(value ?: "{}", UserDB.AssistantToolsDB::class.java)
    }

    @TypeConverter
    fun assistantToolsToString(assistantTools: UserDB.AssistantToolsDB?): String {
        return Gson().toJson(assistantTools ?: UserDB.AssistantToolsDB.empty())
    }

    @TypeConverter
    fun addressFromString(value: String?): UserDB.DetailsDB.AddressDB {
        return Gson().fromJson(value ?: "{}", UserDB.DetailsDB.AddressDB::class.java)
    }

    @TypeConverter
    fun addressToString(address: UserDB.DetailsDB.AddressDB?): String {
        return Gson().toJson(address ?: UserDB.DetailsDB.AddressDB.empty())
    }

    @TypeConverter
    fun credentialsFromString(value: String?): UserDB.DetailsDB.CredentialsDB {
        return Gson().fromJson(value ?: "{}", UserDB.DetailsDB.CredentialsDB::class.java)
    }

    @TypeConverter
    fun preferenceToString(preference: UserDB.DetailsDB.PreferenceDB?): String {
        return Gson().toJson(preference ?: UserDB.DetailsDB.PreferenceDB.empty())
    }

    @TypeConverter
    fun preferenceFromString(value: String?): UserDB.DetailsDB.PreferenceDB {
        return Gson().fromJson(value ?: "{}", UserDB.DetailsDB.PreferenceDB::class.java)
    }
}
