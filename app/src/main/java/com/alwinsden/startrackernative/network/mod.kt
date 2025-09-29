package com.alwinsden.startrackernative.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.URLProtocol

val httpClient = HttpClient(CIO) {
    expectSuccess = true
    defaultRequest {
        url {
            protocol = URLProtocol.HTTP
            host = "localhost"//"10.0.2.2"
            port = 8080
        }
    }
}