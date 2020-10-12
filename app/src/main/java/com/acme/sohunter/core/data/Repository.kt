package com.acme.sohunter.core.data

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.acme.sohunter.core.data.api.ApiService
import com.acme.sohunter.core.domain.Questions
import com.acme.sohunter.framework.Constants
import com.acme.sohunter.framework.networking.WebServiceBuilder
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    fun remoteSource() = WebServiceBuilder.buildService(ApiService::class.java)
    fun getRecentQuestions() : Call<String> = remoteSource().getRecentQuestions()
    private var questionData : MutableList<Questions> = ArrayList()

    fun requestRecentQuestions() : LiveData<List<Questions>> {
        val request = remoteSource()
        val call = request.getRecentQuestions()
        val data : MutableLiveData<List<Questions>> = MutableLiveData()

        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.i("onResponse","Response: ${response.code()}")

                if(response.isSuccessful) {
                    val questions = response.body()

                    questions?.let {
                        questionData = processQuestion(it)
                        data.value = questionData
                    }

                    Log.i("WE_DID_IT","Question Body: ${questions}")
                }

            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i("onFailure","Retrofit Network Error")
            }
        })

        return data
    }


    fun processQuestion(json: String) : MutableList<Questions> {
        var answerCount: Int
        lateinit var title: String
        lateinit var link: String
        var questionsData: Questions
        val questionArray : MutableList<Questions> = ArrayList()

        try {
            val parentObj = JSONObject(json)
            val itemArray: JSONArray = parentObj.getJSONArray("items")
            println("********** Processing Questions **********")
            for (index in 0 until itemArray.length()) {
                val objs = itemArray.getJSONObject(index)

                if (objs.has(Constants.ACCEPTED_ANSWER_ID)) {

                    if (objs.has(Constants.ANSWER_COUNT)) {
                        answerCount = objs.getInt(Constants.ANSWER_COUNT)
                        if (answerCount > 1) {

                            if (objs.has(Constants.TITLE)) {
                                title = objs.getString(Constants.TITLE)
                            }

                            if (objs.has(Constants.LINK)) {
                                link = objs.getString(Constants.LINK)
                            }
                        }

                    }
                }

                questionsData = Questions(link, title)

                Log.i("Data_Object_Link", questionsData.link)
                Log.i("Data_Object_Title", questionsData.title)

                questionArray.add(questionsData)
            }

            Log.i("processLoop Array Size:", "${questionArray.size}")

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return questionArray
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