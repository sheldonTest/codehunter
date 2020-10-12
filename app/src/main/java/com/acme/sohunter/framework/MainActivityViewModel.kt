package com.acme.sohunter.framework

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.acme.sohunter.core.data.api.ApiService
import com.acme.sohunter.core.data.Repository
import com.acme.sohunter.core.domain.Questions
import com.acme.sohunter.framework.networking.WebServiceBuilder
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {

    private val repository: Repository = Repository()
    private val retroData: LiveData<List<Questions>> = repository.requestRecentQuestions()

    fun requestRecentQuestions() : LiveData<List<Questions>> = retroData

}