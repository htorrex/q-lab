package com.inqubits.mobile.qlab.di

import com.inqubits.mobile.qlab.domain.MovieManager
import com.inqubits.mobile.qlab.domain.MovieManagerImpl
import com.inqubits.mobile.qlab.model.MovieRepository
import com.inqubits.mobile.qlab.model.MovieRepositoryImpl
import com.inqubits.mobile.qlab.presentation.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainKoinModule =
        module {
            single<MovieRepository> { MovieRepositoryImpl("http://www.omdbapi.com/", "64da4f7e") }
            single<MovieManager> { MovieManagerImpl(get()) }
            viewModel { MovieViewModel(get()) }
        }
