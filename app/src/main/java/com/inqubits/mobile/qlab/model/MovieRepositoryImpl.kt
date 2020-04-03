package com.inqubits.mobile.qlab.model

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.parameter
import io.ktor.client.request.request
import io.ktor.http.takeFrom
import kotlinx.serialization.json.Json

class MovieRepositoryImpl(private val endPoint: String, private val apiKey: String) : MovieRepository {

    var client: HttpClient = makeHttpClient()

    private fun makeHttpClient(): HttpClient {
        return HttpClient {
            expectSuccess = false
            install(JsonFeature) {
                serializer = KotlinxSerializer(Json.nonstrict).apply {
                    setMapper(SearchResultResponse::class, SearchResultResponse.serializer())
                    setMapper(MovieResponse::class, MovieResponse.serializer())
                }
            }
        }
    }

    override suspend fun searchMovieByTitle(title: String): SearchResultResponse {
        return client.request {
            url {
                takeFrom(endPoint)
                parameter("apikey", apiKey)
                parameter("s", title)
            }
        }
    }
}
