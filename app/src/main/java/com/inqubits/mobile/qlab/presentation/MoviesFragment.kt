package com.inqubits.mobile.qlab.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.inqubits.mobile.qlab.R

class MoviesFragment : Fragment() {

    companion object {
        fun newInstance(params: Bundle): MoviesFragment {
            return MoviesFragment().apply { arguments = params }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_movies_layout, container, false)
    }
}
