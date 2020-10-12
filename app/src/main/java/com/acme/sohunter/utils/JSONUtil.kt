package com.acme.sohunter.utils

import android.util.Log
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

object JSONUtil {

    fun processQuestion(json: String) {

        try {
            val parentObj = JSONObject(json)
            val itemArray : JSONArray = parentObj.getJSONArray("items")
            println("********** Processing Questions **********")
            for(index in 0 until itemArray.length()) {
                val objs = itemArray.getJSONObject(index)

                if(objs.has("is_answered")) {
                    val isAnswered = objs.getBoolean("is_answered")
                    println("is_answered values: ${isAnswered}")
                }

                if(objs.has("accepted_answer_id")) {
                    val acceptId = objs.getInt("accepted_answer_id")
                    Log.i("accepted_answer_id","${acceptId}")
                }

                if(objs.has("answer_count")) {
                    val answerCount = objs.getInt("answer_count")
                    if(answerCount >= 2) {
                        Log.i("Answer_Count"," ${answerCount}")
                    }
                }

                if(objs.has("title")) {
                    val title = objs.getString("title")
                     Log.i("Title","${title}")
                }

                //TODO: Build the Data object array here
            }

        }
        catch(e: JSONException) {
            e.printStackTrace()
        }
    }


}