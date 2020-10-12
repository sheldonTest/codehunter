package com.acme.sohunter.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.acme.sohunter.R
import com.acme.sohunter.framework.MainActivityViewModel


class MainActivity : AppCompatActivity(), ViewModelStoreOwner {

    private lateinit var viewModel : MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        testQuestionArray()
    }

    private fun testQuestionArray() {
        viewModel.requestRecentQuestions().observe(this, Observer { questions ->

            Log.i("Questions_testQuestionArray","${questions.size}")
        })
    }

}