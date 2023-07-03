package com.teko.domain

data class Session(
    var user: User,
    var accessToken: AccessToken
)
