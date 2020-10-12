package com.acme.sohunter.core.data

import com.acme.sohunter.core.data.api.ApiService
import com.acme.sohunter.core.domain.Questions
import com.acme.sohunter.framework.JSONUtil
import com.acme.sohunter.framework.networking.WebServiceBuilder
import retrofit2.Call

class Repository {

    fun remoteSource() = WebServiceBuilder.buildService(ApiService::class.java)
    fun getRecentQuestions() : Call<String> = remoteSource().getRecentQuestions()
    fun getQuestionData() : ArrayList<Questions> = JSONUtil.getQuestionData()

}