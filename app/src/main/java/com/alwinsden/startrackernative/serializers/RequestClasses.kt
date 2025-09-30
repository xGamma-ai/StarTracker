package com.alwinsden.startrackernative.serializers

import kotlinx.serialization.Serializable

@Serializable
data class LoginDataClass(
    val user_name: String,
    val user_password: String
)