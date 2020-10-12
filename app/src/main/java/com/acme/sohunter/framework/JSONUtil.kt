package com.acme.sohunter.framework

import android.util.Log
import com.acme.sohunter.core.domain.Questions
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

object JSONUtil {

    private val questionArray = ArrayList<Questions>()

    fun processQuestion(json: String) {
        var answerCount : Int
        var title : String
        var link : String
        var questionData : Questions

        try {
            val parentObj = JSONObject(json)
            val itemArray : JSONArray = parentObj.getJSONArray("items")
            println("********** Processing Questions **********")
            for(index in 0 until itemArray.length()) {
                val objs = itemArray.getJSONObject(index)

                if(objs.has(Constants.ACCEPTED_ANSWER_ID)) {

                    if(objs.has(Constants.ANSWER_COUNT) ) {
                         answerCount = objs.getInt(Constants.ANSWER_COUNT)
                        if(answerCount > 1) {

                            if(objs.has(Constants.TITLE)) {
                                 title = objs.getString(Constants.TITLE)
                                Log.i("Title","${title}")
                            }

                            if(objs.has(Constants.LINK)) {
                                link = objs.getString(Constants.LINK)
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

    fun getQuestionData() : ArrayList<Questions> = questionArray


}