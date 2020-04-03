package com.inqubits.mobile.qlab.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResultResponse(
        @SerialName("Search")
        val searchResult: List<MovieResponse>,

        val totalResults: String,

        @SerialName("Response")
        val response: String
)

@Serializable
data class MovieResponse(
        @SerialName("Title")
        val title: String,

        @SerialName("Year")
        val year: String,

        val imdbID: String,

        @SerialName("Type")
        val type: String,

        @SerialName("Poster")
        val posterUrl: String
)
