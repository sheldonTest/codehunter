package com.acme.sohunter.ui

import android.util.Log
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.acme.sohunter.R
import com.acme.sohunter.data.api.ApiService
import com.acme.sohunter.data.repository.Repository
import com.acme.sohunter.utils.JSONUtil
import com.acme.sohunter.utils.WebServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SOViewModel(private val repository: Repository) : ViewModel() {


     fun requestRecentQuestions() {
        val request = repository.remoteSource()
        val call = request.getRecentQuestions()

        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.i("onResponse","Response: ${response.code()}")

                if(response.isSuccessful) {
                    val questions = response.body()
                    questions?.let { JSONUtil.processQuestion(it) }
                    Log.i("WE_DID_IT","Question Body: ${questions}")
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i("onFailure","Retrofit Network Error")
            }

        })
    }

    private fun callOAuth2Service(context: AppCompatActivity) {
        val request = WebServiceBuilder.buildService(ApiService::class.java)
        val call = request.oauthHtmlRequest()

        call.enqueue(object : Callback<String> {

            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.i("onResponse","Auth Code: ${response.code()}")
                if(response.isSuccessful) {
                    val html = response.body()
                    Log.i("WE_DID_IT","Auth Token: ${html}")

                    /*
                    val webView = context.findViewById<WebView>(R.id.web)
                    webView.apply {
                        settings.loadsImagesAutomatically = true
                        settings.javaScriptEnabled = true
                        settings.domStorageEnabled = true
                        settings.useWideViewPort = true
                        settings.loadWithOverviewMode = true
                    }


                    if (html != null) {
                        webView.loadDataWithBaseURL(null,html,"text/html","utf-8",null)
                    }
                    */
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {

            }
        })
    }

}