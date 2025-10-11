package com.alwinsden.startrackernative.network

import com.alwinsden.startrackernative.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.URLProtocol
import io.ktor.serialization.gson.gson
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

val httpClient = HttpClient(CIO) {
    val server_base_url = BuildConfig.BASE_URL
    println(server_base_url)
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        })
    }
    expectSuccess = true
    defaultRequest {
        url {
            protocol = URLProtocol.HTTP
            host = server_base_url
            port = 8080
        }
    }
}