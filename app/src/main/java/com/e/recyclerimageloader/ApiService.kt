package com.e.recyclerimageloader

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object ApiService {
    const val URL = "https://api.themoviedb.org/3/"

    fun getData(): MovieApi{
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(MovieApi::class.java)
    }

    interface MovieApi{
        @GET("movie/popular")
        fun getPopularMovies(@Query("api_key")apiKey: String): Call<MovieResponse>
    }
}