package com.dima.financeapp.ui.main.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dima.financeapp.App
import com.dima.financeapp.R
import com.dima.financeapp.ui.main.nav.NavFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val navFragment = NavFragment()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mainViewModel: MainViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as App).appComponent.inject(this)
        setContentView(R.layout.activity_main)

        mainViewModel.getUser()
        mainViewModel.getBills()

        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_main_container, navFragment)
            .commit()
    }
}