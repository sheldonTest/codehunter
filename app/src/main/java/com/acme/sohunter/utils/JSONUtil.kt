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

                if(objs.has("accepted_answer_id")) {

                    if(objs.has("answer_count") ) {
                        val answerCount = objs.getInt("answer_count")
                        if(answerCount > 1) {

                            if(objs.has("title")) {
                                val title = objs.getString("title")
                                Log.i("Title","${title}")
                            }

                            if(objs.has("link")) {
                                val link = objs.getString("link")
                                Log.i("Link","${link}")
                            }

                            Log.i("Answer_Count"," ${answerCount}")
                        }
                    }
                }

                //TODO: Build the Data object array here
            }

        }
        catch(e: JSONException) {
            e.printStackTrace()
        }
    }


}