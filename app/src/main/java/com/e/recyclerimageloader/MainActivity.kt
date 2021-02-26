package com.e.recyclerimageloader

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.WorkerThread
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.appbar.MaterialToolbar
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var recView: RecyclerView
    private lateinit var toolbar: MaterialToolbar
    private lateinit var progressBar: ProgressBar
    private lateinit var sw: SwipeRefreshLayout

    private lateinit var adapter: MovieListAdapter
    private var movieList: MutableList<Movie> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        progressBar = findViewById(R.id.progressBar)

        recView = findViewById(R.id.recView)


        val layoutManager = LinearLayoutManager(applicationContext)
        recView.layoutManager = layoutManager
        layoutManager.orientation = LinearLayoutManager.VERTICAL



        adapter = MovieListAdapter(movieList)
        recView.adapter = adapter
        adapter!!.notifyDataSetChanged()



        getData()

    }

    fun getData(){
        progressBar.visibility = View.VISIBLE
        ApiService.getData().getPopularMovies("633b0d5400a1437826672c9966199c0b").enqueue(object : Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                progressBar.visibility = View.GONE
                Toast.makeText(applicationContext, "BAD CONNECTION", Toast.LENGTH_SHORT).show()
                t!!.printStackTrace()
            }

            override fun onResponse(
                call: Call<MovieResponse>?,
                response: Response<MovieResponse>?
            ) {
                progressBar.visibility = View.GONE
                val list = response?.body()!!.getResults()
                adapter.movieList = list
                adapter.notifyDataSetChanged()
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.ITEM_refresh -> {
                Toast.makeText(applicationContext, "Refresh", Toast.LENGTH_SHORT).show()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }




}