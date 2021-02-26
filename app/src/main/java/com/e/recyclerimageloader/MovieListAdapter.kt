package com.e.recyclerimageloader

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieListAdapter(var movieList: List<Movie>): RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    inner class ViewHolder(val view: View): RecyclerView.ViewHolder(view){
        lateinit var movieImage: ImageView
        lateinit var movieName: TextView
        lateinit var moviePopularity: TextView


        fun init(){
            movieImage = view.findViewById(R.id.IV_movie_image)
            movieName = view.findViewById(R.id.TV_movie_name)
            moviePopularity = view.findViewById(R.id.TV_movie_popularity)
        }

        fun bind(movie: Movie?){
            init()

            Glide.with(view)
                    .load(movie?.getPosterPath())
                    .into(movieImage)

            movieName.text = movie?.title
            moviePopularity.text = movie?.popularity.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_model, null, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieListAdapter.ViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

}