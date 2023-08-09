package com.teko.techdata.remote

import com.teko.techdata.remote.features.auth.dto.LoginResponse
import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface TechApiServices {

    @POST("users/login")
    fun login(@Body payload: RequestBody): Single<Response<LoginResponse>>

    @Headers(
        "Cache-Control: no-cache",
        "Accept-Encoding: identity",
        "Host: staging-api.teko.ph",
        "Connection: Keep-Alive"
    )
    @GET("users/login")
    fun checkSession(): Single<LoginResponse>
}
