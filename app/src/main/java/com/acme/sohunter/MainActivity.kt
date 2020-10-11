package com.acme.sohunter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import com.acme.sohunter.data.api.ApiService
import com.acme.sohunter.data.repository.Repository
import com.acme.sohunter.ui.SOViewModel
import com.acme.sohunter.utils.WebServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : SOViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = SOViewModel(Repository())
        viewModel.requestRecentQuestions()
    }

}