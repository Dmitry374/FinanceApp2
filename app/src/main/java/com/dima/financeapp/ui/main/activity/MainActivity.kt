package com.dima.financeapp.ui.main.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dima.financeapp.R
import com.dima.financeapp.ui.main.nav.NavFragment

class MainActivity : AppCompatActivity() {

    private val navFragment = NavFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_main_container, navFragment)
            .commit()
    }
}