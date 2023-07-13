package com.teko.techdata.remote

import com.teko.techdata.remote.features.auth.dto.LoginResponse
import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TechApiServices {

    @POST
    fun login(@Body payload: RequestBody): Single<Response<LoginResponse>>
}
