package com.teko.network

import com.teko.network.features.auth.models.LoginResponse
import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TekoTechApiServices {

    @POST
    fun login(@Body payload: RequestBody): Single<Response<LoginResponse>>
}
