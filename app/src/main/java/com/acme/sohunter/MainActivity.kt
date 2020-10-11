package com.acme.sohunter

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import com.acme.sohunter.model.ApiService
import com.acme.sohunter.model.OAuthResponse
import com.acme.sohunter.utils.OAuthServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URLEncoder


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //callOAuth2Service(this)
        requestRecentQuestions()
    }

    private fun requestRecentQuestions() {
        val request = OAuthServiceBuilder.buildService(ApiService::class.java)
        val call = request.getRecentQuestions()

        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.i("onResponse","Response: ${response.code()}")

                if(response.isSuccessful) {
                    val questions = response.body()
                    Log.i("WE_DID_IT","Question Body: ${questions}")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i("HELL_NO","YOU BLEW IT MOTHER FUCKER!!!")
            }

        })
    }

    private fun callOAuth2Service(context: Context) {
        val request = OAuthServiceBuilder.buildService(ApiService::class.java)
        val call = request.oauthHtmlRequest()

        call.enqueue(object : Callback<String> {

            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.i("onResponse","Auth Code: ${response.code()}")
                if(response.isSuccessful) {
                    val html = response.body()
                    Log.i("WE_DID_IT","Auth Token: ${html}")

                    val webView = findViewById<WebView>(R.id.web)
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
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {

            }
        })

    }
}