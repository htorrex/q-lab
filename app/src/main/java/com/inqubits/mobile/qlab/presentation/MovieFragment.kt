package com.inqubits.mobile.qlab.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.inqubits.mobile.qlab.databinding.FragmentMovieLayoutBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {

    private lateinit var fragmentBinding: FragmentMovieLayoutBinding
    val movieViewModel: MovieViewModel by viewModel()

    companion object {
        fun newInstance(params: Bundle): MovieFragment {
            return MovieFragment().apply { arguments = params }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        fragmentBinding = FragmentMovieLayoutBinding.inflate(inflater, container, false)
        return fragmentBinding.rootLayout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val textMovieTitle = fragmentBinding.textMovieTitle
        val movieAdapter = MovieAdapter(fragmentBinding.rootLayout.context)

        fragmentBinding.recyclerView.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(context)
        }

        textMovieTitle.setOnEditorActionListener { v, actionId, event ->
            when (actionId) {
                EditorInfo.IME_ACTION_GO -> {
                    movieViewModel.findMovieByTitle(textMovieTitle.text.toString()) // making the request
                    textMovieTitle.hideKeyboard()
                }
            }
            return@setOnEditorActionListener true
        }

        // Positive response
        movieViewModel.movieLiveData.observe(viewLifecycleOwner, Observer { movies ->
            movieAdapter.setData(movies)
        })

        // Error Listener
        movieViewModel.errorAlert.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })
    }
}
