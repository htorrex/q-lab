package com.inqubits.mobile.qlab.model

interface MovieRepository {
    suspend fun searchMovieByTitle(title: String): SearchResultResponse
}
