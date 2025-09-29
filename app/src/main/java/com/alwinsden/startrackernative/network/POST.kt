package com.alwinsden.startrackernative.network

import io.ktor.client.request.request
import io.ktor.http.HttpMethod

suspend fun LoginRequest(username: String, userPassword: String) {
    val response = httpClient.request("/login") {
        method = HttpMethod.Post
    }
}