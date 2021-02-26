package com.e.recyclerimageloader
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Movie(
        @SerializedName("popularity")
        val popularity : Double,
        @SerializedName("poster_path")
        val poster_path : String,
        @SerializedName("title")
        val title : String,
        ): Serializable{
    fun getPosterPath(): String {
        return "https://image.tmdb.org/t/p/w500$poster_path"
    }

}