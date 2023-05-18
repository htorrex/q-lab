package com.inqubits.mobile.qlab.model

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class MovieRepositoryImpl(private val endPoint: String, private val apiKey: String) :
    MovieRepository {

    var client: HttpClient = makeHttpClient()

    private fun makeHttpClient(): HttpClient {
        return HttpClient {
            expectSuccess = false
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
        }
    }

    override suspend fun searchMovieByTitle(title: String): SearchResultResponse {
        return client.get(endPoint) {
            url {
                parameters.append("apikey", apiKey)
                parameters.append("s", title)
            }
        }.body()
    }
}
