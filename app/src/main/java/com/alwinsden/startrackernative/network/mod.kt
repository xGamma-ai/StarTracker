package com.alwinsden.startrackernative.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.URLProtocol
import io.ktor.serialization.gson.gson
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

val httpClient = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
        })
    }
    expectSuccess = true
    defaultRequest {
        url {
            protocol = URLProtocol.HTTP
            host = "localhost"//"10.0.2.2"
            port = 8080
        }
    }
}