package com.alwinsden.startrackernative.network

import android.util.Log
import com.alwinsden.startrackernative.serializers.LoginDataClass
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
        println(response.status)
    } catch (e: Exception) {
        Log.d("LOGIN_REQUEST", e.toString())
    }
}