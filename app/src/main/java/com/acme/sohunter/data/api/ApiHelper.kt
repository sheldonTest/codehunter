package com.acme.sohunter.data.api

import com.acme.sohunter.utils.WebServiceBuilder

class ApiHelper(private val apiService: ApiService) {

    fun remoteSource() = WebServiceBuilder.buildService(ApiService::class.java)
    fun getQuestions() = apiService.getRecentQuestions()

}