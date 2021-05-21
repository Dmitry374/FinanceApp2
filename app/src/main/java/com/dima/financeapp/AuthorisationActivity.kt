package com.dima.financeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AuthorisationActivity : AppCompatActivity() {

    private var authorisationFragment = AuthorisationFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorisation)

        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_authorisation_container, authorisationFragment)
            .commit()
    }
}