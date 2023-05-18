package com.inqubits.mobile.qlab.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inqubits.mobile.qlab.domain.Failure
import com.inqubits.mobile.qlab.domain.MovieManager
import com.inqubits.mobile.qlab.domain.Success
import com.inqubits.mobile.qlab.model.Movie
import kotlinx.coroutines.launch

class MovieViewModel(private val movieManagerImpl: MovieManager) : ViewModel() {

    private val resultLiveData = MutableLiveData<List<Movie>>() // result from the request

    val movieLiveData: LiveData<List<Movie>>  // Observe immutable Live Data positive event.
        get() = resultLiveData

    var errorAlert = MutableLiveData<String>()  // Observe Live Data for error event.

    // public method to be invoke from the UI
    fun findMovieByTitle(title: String) {
        viewModelScope.launch {
            when (val result = movieManagerImpl.getMovieByTitle(title)) {
                is Success -> resultLiveData.value = result.value
                is Failure -> errorAlert.value = "No Data found!"
            }
        }
    }
}
