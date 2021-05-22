package com.dima.financeapp.ui.authorisation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dima.financeapp.R
import com.dima.financeapp.ui.authorisation.communication.FragmentAuthorisationCommunication
import com.dima.financeapp.ui.main.MainActivity

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

    override fun goToMainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }
}