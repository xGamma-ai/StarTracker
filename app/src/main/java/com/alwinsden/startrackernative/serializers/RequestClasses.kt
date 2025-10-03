package com.alwinsden.startrackernative.serializers

import kotlinx.serialization.Serializable

@Serializable
data class LoginDataClass(
    val user_email: String,
    val user_password: String
)