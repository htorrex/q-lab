package com.inqubits.mobile.qlab.presentation

import android.util.Log
import androidx.lifecycle.*
import com.inqubits.mobile.qlab.domain.Failure
import com.inqubits.mobile.qlab.domain.MovieManager
import com.inqubits.mobile.qlab.domain.Success
import com.inqubits.mobile.qlab.model.Movie

class MovieViewModel(val movieManagerImpl: MovieManager) : ViewModel() {

    // request from the UI
    private val requestData = MutableLiveData<String>()

    // Observe Live Data and process positive event.
    var resultData = MediatorLiveData<List<Movie>>()

    // Observe Live Data and process error event.
    var errorAlert = MutableLiveData<String>()

    init {
        setupMovieLiveData()
    }

    // public method to be invoke from the UI
    fun findMovieByTitle(title: String) {
        requestData.value = title
    }

    private fun setupMovieLiveData() {
        val response = Transformations.switchMap(requestData) {
            liveData {
                emit(movieManagerImpl.getMovieByTitle(it))
            }
        }

        resultData.addSource(response) {
            when (it) {
                is Success -> resultData.value = it.value
                is Failure -> {
                    Log.d("MOVIE", "Something wrong: ${it.error.message}")
                    errorAlert.value = "No Data found!"
                }
            }
        }
    }
}
