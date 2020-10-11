package com.acme.sohunter.data.repository

import com.acme.sohunter.data.api.ApiHelper
import com.acme.sohunter.data.api.ApiService
import com.acme.sohunter.utils.WebServiceBuilder
import retrofit2.Call

class Repository {

    fun remoteSource() = WebServiceBuilder.buildService(ApiService::class.java)
    fun getRecentQuestions() : Call<String> = remoteSource().getRecentQuestions()


}