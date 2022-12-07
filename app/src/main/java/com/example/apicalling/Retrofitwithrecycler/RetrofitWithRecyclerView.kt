package com.example.apicalling.Retrofitwithrecycler

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apicalling.R
import com.example.apicalling.Retrofitwithrecycler.Adapter.CharacterAdapter
import retrofit2.Call
import retrofit2.Response

class RetrofitWithRecyclerView : AppCompatActivity() {
    private lateinit var mRecyclerView: RecyclerView
    lateinit var mRecyclerViewAdapter: CharacterAdapter

    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_with_recycler_view)

        progressBar = findViewById(R.id.progressBar)
        progressBar.visibility = View.VISIBLE
        mRecyclerView = findViewById(R.id.characterRecyclerView)


        mRecyclerViewAdapter = CharacterAdapter(this)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = mRecyclerViewAdapter


        val retrofit = RetroFitClient.apiService
        val call = retrofit.fetchCharacters()

        call.enqueue(object : retrofit2.Callback<MutableList<CharacterModel>> {
            override fun onResponse(
                call: Call<MutableList<CharacterModel>>,
                response: Response<MutableList<CharacterModel>>,
            ) {
                Log.d("TAG", "onResponse: ${response.body()}")
                val characters = response.body()

                val characterList = mutableListOf<CharacterModel>()

                characters?.let { characterList.addAll(it) }
                mRecyclerViewAdapter.setCharacterList(characterList)
                progressBar.visibility = View.GONE

                Log.d("TAG", "Success " + response.body().toString())

            }

            override fun onFailure(call: Call<MutableList<CharacterModel>>, t: Throwable) {
                Toast.makeText(baseContext, "Failed : $t", Toast.LENGTH_SHORT).show()
            }

        })

    }
}