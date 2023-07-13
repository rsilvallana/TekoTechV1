package com.teko.network.base

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

open class BaseRemoteSource {
    fun getJsonRequestBody(jsonString: String) =
        jsonString.toRequestBody("application/json".toMediaTypeOrNull())
}
