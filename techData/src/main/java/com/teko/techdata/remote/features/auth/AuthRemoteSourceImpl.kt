package com.teko.techdata.remote.features.auth

import com.google.gson.JsonObject
import com.teko.techdata.remote.features.auth.domain.AccessToken
import com.teko.techdata.remote.features.auth.domain.User
import com.teko.commondata.remote.BaseRemoteSource
import com.teko.techdata.remote.TechApiServices
import com.teko.techdata.remote.features.auth.dto.LoginResponse
import io.reactivex.Single
import javax.inject.Inject

class AuthRemoteSourceImpl @Inject constructor(
    private val techApiServices: TechApiServices
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
        return techApiServices
            .login(payload)
            .map { response ->
                val token = response.headers()["set-cookie"]?.find { it.toString() == "tk-ss" }.toString()
                LoginResponse.mapLoginResponse(
                    loginResponse = response.body() ?: LoginResponse.empty(),
                    token = token
                )
            }
    }

    override fun checkSession(): Single<User> {
        return techApiServices
            .checkSession()
            .map {
                LoginResponse.toDomain(it)
            }
    }
}
