package com.inqubits.mobile.qlab.domain

import android.util.Log
import com.inqubits.mobile.qlab.model.Movie
import com.inqubits.mobile.qlab.model.MovieRepository
import com.inqubits.mobile.qlab.model.MovieResponse

class MovieManagerImpl(val movieRepository: MovieRepository) : MovieManager {

    override suspend fun getMovieByTitle(title: String): Result<List<Movie>> {
        return try {
            val searchResponse = movieRepository.searchMovieByTitle(title)
            val movies = searchResponse.searchResult.map { transformToMovie(it) }
            Success(movies)
        } catch (e: Exception) {
            Log.d("MOVIE", "Something wrong: ${e.message}")
            Failure(e)
        }
    }

    private fun transformToMovie(movieResponse: MovieResponse): Movie {
        return Movie(movieResponse.title, movieResponse.year, movieResponse.posterUrl)
    }
}
