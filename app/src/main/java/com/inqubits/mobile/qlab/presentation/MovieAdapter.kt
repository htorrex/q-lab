package com.inqubits.mobile.qlab.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.inqubits.mobile.qlab.databinding.MovieItemLayoutBinding
import com.inqubits.mobile.qlab.model.Movie

class MovieAdapter(private val context: Context) : RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder>() {

    private var movies: List<Movie> = emptyList()

    fun setData(items: List<Movie>) {
        movies = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapterViewHolder {
        val binding = MovieItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return MovieAdapterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieAdapterViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    inner class MovieAdapterViewHolder(private val movieItemLayoutBinding: MovieItemLayoutBinding) : RecyclerView.ViewHolder(movieItemLayoutBinding.baseLayout) {
        fun bind(movie: Movie) {
            movieItemLayoutBinding.textTitle.text = movie.title
            movieItemLayoutBinding.textYear.text = movie.year
            Glide.with(context).load(movie.posterUrl).override(context.resources.displayMetrics.widthPixels / 2, context.resources.displayMetrics.heightPixels / 2).centerInside().into(movieItemLayoutBinding.imgPoster)
        }
    }
}