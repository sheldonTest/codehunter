package com.acme.sohunter.core.data.api

import com.acme.sohunter.framework.networking.WebServiceBuilder

class ApiHelper(private val apiService: ApiService) {

    fun remoteSource() = WebServiceBuilder.buildService(ApiService::class.java)
    fun getQuestions() = apiService.getRecentQuestions()

}