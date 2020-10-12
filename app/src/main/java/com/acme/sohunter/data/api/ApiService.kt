package com.acme.sohunter.data.api

import com.acme.sohunter.data.model.OAuthResponse
import com.acme.sohunter.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.AUTH_PARAMS)
    fun oauthRequest(
        @Query("client_id") clientId: Int = 18910,
        @Query("scope")scope: String = "no_expiry",
        @Query("redirect_uri") redirectUri: String = "https://stackoverflow.com/oauth/login_success") : Call<OAuthResponse>

    @Headers("Accept: text/html")
    @GET(Constants.AUTH_PARAMS)
    fun oauthHtmlRequest() : Call<String>

    @GET(Constants.RECENT_QUESTIONS_PARAMS)
    fun getRecentQuestions( @Query("order") order: String = "desc",
                            @Query("sort") sort: String = "month",
                            @Query("site") site: String = "stackoverflow"): Call<String>



}