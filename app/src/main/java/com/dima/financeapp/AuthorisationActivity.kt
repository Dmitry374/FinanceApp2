package com.dima.financeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AuthorisationActivity : AppCompatActivity(), FragmentAuthorisationCommunication {

    private val authorisationFragment = AuthorisationFragment()
    private val loginFragment = LoginFragment()
    private val registrationFragment = RegistrationFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorisation)

        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_authorisation_container, authorisationFragment)
            .commit()
    }

    override fun onOpenLoginScreen() {
        supportFragmentManager.beginTransaction()
            .add(R.id.nav_host_authorisation_container, loginFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onOpenRegistrationScreen() {
        supportFragmentManager.beginTransaction()
            .add(R.id.nav_host_authorisation_container, registrationFragment)
            .addToBackStack(null)
            .commit()
    }
}