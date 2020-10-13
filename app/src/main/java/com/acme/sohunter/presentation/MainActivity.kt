package com.acme.sohunter.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.acme.sohunter.R
import com.acme.sohunter.framework.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), ViewModelStoreOwner {

    private lateinit var viewModel : MainActivityViewModel
    private lateinit var linearLayerManager: LinearLayoutManager
    private lateinit var  soAdapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayerManager = LinearLayoutManager(this)
        sorecycler.layoutManager = linearLayerManager

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        testQuestionArray()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun testQuestionArray() {
        viewModel.requestRecentQuestions().observe(this, Observer { questions ->
            soAdapter = RecyclerAdapter(questions)

            sorecycler.adapter = soAdapter
            Log.i("Questions_testQuestionArray","${questions.size}")
        })
    }

}