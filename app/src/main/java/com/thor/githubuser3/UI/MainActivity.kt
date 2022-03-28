package com.thor.githubuser3.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.thor.githubuser3.R
import com.thor.githubuser3.UI.Home.HomeViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}