package com.acme.sohunter.data.model

import com.google.gson.annotations.SerializedName

data class OAuthResponse (@SerializedName("access_token") var access_token: String,@SerializedName("expires") var expires: String)