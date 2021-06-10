package com.dicoding.expert.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.expert.core.R
import com.dicoding.expert.core.databinding.ItemMovieBinding
import com.dicoding.expert.core.domain.model.Movie
import com.dicoding.expert.core.utils.IMovieNote
import java.util.ArrayList

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.ListViewHolder>() {
    private var listMovie = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun movieData(movieList: List<Movie>?) {
        if (movieList == null) return
        listMovie.clear()
        listMovie.addAll(movieList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))


    override fun onBindViewHolder(holder: MovieAdapter.ListViewHolder, position: Int) {
        val movieData = listMovie[position]
        holder.bind(movieData)
    }

    override fun getItemCount(): Int = listMovie.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMovieBinding.bind(itemView)
        fun bind(data: Movie) {
            with(binding) {
                Glide.with(itemView.context)
                    .load("${IMovieNote.POSTER}${data.poster_path}")
                    .into(posterMovie)
                tvMovieTitle.text = data.title
                tvMovieLaunching.text = data.release_date
                tvMoviePopular.text = data.popularity.toString()
            }
        }
        init {
            binding.root.setOnClickListener{
                onItemClick?.invoke(listMovie[adapterPosition])
            }
        }
    }
}