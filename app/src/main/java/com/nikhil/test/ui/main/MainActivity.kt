package com.nikhil.test.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nikhil.test.R
import dagger.hilt.android.AndroidEntryPoint

/**
 *  Launcher Activity ,
 *  contains fragments to show List
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}