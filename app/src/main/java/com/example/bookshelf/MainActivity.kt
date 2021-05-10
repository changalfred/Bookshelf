package com.example.bookshelf

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.bookshelf.repository.network.GoogleBooksApi
import com.example.bookshelf.repository.network.createApiService
import com.example.bookshelf.repository.network.model.BooksResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView = findViewById<BottomNavigationView>(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val service = createApiService(GoogleBooksApi::class.java)
        val response = service.getBooksResponse("water", "<insert API key here>")
        response.enqueue(object: Callback<BooksResponse> {
            override fun onResponse(call: Call<BooksResponse>, response: Response<BooksResponse>) {
                val isSuccessful = response.isSuccessful

                if (isSuccessful) {
                    // TODO: Process result here.
                } else {
                    Timber.d("ALFRED CHANG -- Fail response: ${response.code()}, ${response.body()}")
                }
            }

            override fun onFailure(call: Call<BooksResponse>, t: Throwable) {
                Timber.d("ALFRED CHANG -- Error response: ${t.message}")
            }
        })
    }
}