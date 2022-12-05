@file:Suppress("DEPRECATION")

package com.example.apicalling

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatButton
import kotlinx.coroutines.*
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        val responseTextView: TextView? = findViewById(R.id.response_TextView)

        val apiCallButton: AppCompatButton? = findViewById(R.id.apiCall_Button)

        apiCallButton?.setOnClickListener(View.OnClickListener {
            apiCalling(responseTextView)
        })

    }

    private fun apiCalling(responseTextView: TextView?) {
        GlobalScope.launch {
                dataFetching(responseTextView)
        }
    }

    private suspend fun dataFetching(responseTextView: TextView?) {
        val url =
            URL("https://raw.githubusercontent.com/irontec/android-kotlin-samples/master/common-data/bilbao.json")

        val result = GlobalScope.async {
            url.openStream().bufferedReader().use {
                it.readText()
            }
        }

        withContext(Dispatchers.Main)
        {
            responseTextView?.text = result.await()
        }
    }
}