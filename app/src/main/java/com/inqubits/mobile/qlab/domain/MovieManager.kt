package com.inqubits.mobile.qlab.domain

import com.inqubits.mobile.qlab.model.Movie

interface MovieManager {
    suspend fun getMovieByTitle(title: String): Result<List<Movie>>
}
