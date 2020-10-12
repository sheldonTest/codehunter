package com.acme.sohunter.core.domain

import com.google.gson.annotations.SerializedName

data class OAuthResponse (@SerializedName("access_token") var access_token: String,@SerializedName("expires") var expires: String)