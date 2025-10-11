package com.alwinsden.startrackernative.network

import android.util.Log
import com.alwinsden.startrackernative.serializers.LoginDataClass
import com.alwinsden.startrackernative.serializers.UserPostLoginDetails
import io.ktor.client.call.body
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType

suspend fun loginRequest(userEmail: String, userPassword: String) {
    try {
        val response: HttpResponse = httpClient.request("/login") {
            method = HttpMethod.Post
            contentType(ContentType.Application.Json)
            setBody(LoginDataClass(userEmail, userPassword))
        }
        val json_response = response.body<UserPostLoginDetails>()
        println(json_response.jwt_token)
    } catch (e: Exception) {
        Log.d("LOGIN_REQUEST", e.toString())
    }
}