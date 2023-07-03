package com.teko.network.features.auth

import com.google.gson.JsonObject
import com.teko.domain.AccessToken
import com.teko.domain.User
import com.teko.network.TekoTechApiServices
import com.teko.network.base.BaseRemoteSource
import com.teko.network.features.auth.models.LoginResponse
import io.reactivex.Single
import javax.inject.Inject

class AuthRemoteSourceImpl @Inject constructor(
    private val apiServices: TekoTechApiServices
) : BaseRemoteSource(), AuthRemoteSource {

    override fun login(
        email: String,
        password: String,
        isSpecial: Boolean
    ): Single<Pair<User, AccessToken>> {
        val json = JsonObject()
            .apply {
                addProperty("email", email)
                addProperty("password", password)
                addProperty("isSpecial", isSpecial)
            }
        val payload = getJsonRequestBody(json.toString())
        return apiServices
            .login(payload)
            .map { response ->
                val token = response.headers()["set-cookie"]?.find { it.toString() == "tk-ss" }.toString()
                LoginResponse.mapLoginResponse(
                    loginResponse = response.body() ?: LoginResponse.empty(),
                    token = token
                )
            }
    }
}
