package com.teko.techdata.remote.features.auth.domain

data class Session(
    var user: User,
    var accessToken: AccessToken
)
