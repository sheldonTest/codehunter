package com.acme.sohunter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.acme.sohunter.ui.SOViewModel


class MainActivity : AppCompatActivity(), ViewModelStoreOwner {

    private lateinit var viewModel : SOViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(SOViewModel::class.java)
        viewModel.requestRecentQuestions()
    }

}