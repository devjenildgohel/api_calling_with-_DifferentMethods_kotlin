package com.example.apicalling

import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitActivity : AppCompatActivity() {

    lateinit var heroNameTextView : AppCompatTextView
    lateinit var heroDescTextView : AppCompatTextView
    lateinit var heroIDTextView : AppCompatTextView
    lateinit var heroImageView : AppCompatImageView
    lateinit var heroPowerStatsView : AppCompatTextView
    lateinit var progressBar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        heroNameTextView  = findViewById(R.id.heroNameTextView)
        heroDescTextView = findViewById(R.id.heroDescriptionTextView)
        heroIDTextView = findViewById(R.id.heroIDTextView)
        heroImageView = findViewById(R.id.heroImageView)
        heroPowerStatsView = findViewById(R.id.heroPowerStatesTextView)

        progressBar = findViewById(R.id.progressBar)
        progressBar.isVisible = true

        getData()
    }

    private fun getData() {
        val retrofit = RetrofitAPI.create()
        val call : Call<DataModel?>? = retrofit.getHero()

        call!!.enqueue(object : Callback<DataModel?> {
            override fun onResponse(call: Call<DataModel?>, response: Response<DataModel?>) {
                Toast.makeText(baseContext, "Response $response", Toast.LENGTH_SHORT).show()
                if (response.isSuccessful) {
                    try {
                        val heroID : String = response.body()!!.id
                        val heroName : String = response.body()!!.name
                        val heroDesc : String = response.body()!!.slug
                        val heroImage : String = response.body()!!.images.get("lg").toString()
                        val heroPowerStats : String = response.body()!!.powerstats.get("intelligence").toString()

                        heroNameTextView.text = "Name : $heroName"
                        heroIDTextView.text = "ID :  $heroID"
                        heroDescTextView.text = "Hero Slug : $heroDesc"
                        heroPowerStatsView.text = "Inteligence : $heroPowerStats"

                        Glide.with(baseContext).load(heroImage).into(heroImageView)

                        progressBar.isVisible = false

                    }
                    catch (e:java.lang.Exception)
                    {
                        Log.e("TAG", "onResponse: $e")
                    }
                }
            }

            override fun onFailure(call: Call<DataModel?>, t: Throwable) {
                Log.e("TAG", "onFailure: $t")
            }
        })

        }
}