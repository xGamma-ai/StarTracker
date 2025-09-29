package com.alwinsden.startrackernative.network

import android.util.Log
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse

suspend fun healthCheckStatus(): Boolean {
    try {
        val checkStateResponse: HttpResponse = httpClient.request("/health")
        println("Health check response: ${checkStateResponse.status}")
        Log.d("HEALTH_CHECK", checkStateResponse.status.toString())
        return true
    } catch (e: Exception) {
        Log.d("HEALTH_CHECK", e.toString())
        return false
    }
}