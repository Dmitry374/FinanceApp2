package com.dima.financeapp.ui.start

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dima.financeapp.App
import com.dima.financeapp.R
import com.dima.financeapp.ui.authorisation.AuthorisationActivity
import com.dima.financeapp.ui.main.activity.MainActivity
import com.dima.financeapp.utils.EventObserver
import javax.inject.Inject

class StartActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val startViewModel: StartViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as App).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        window.setBackgroundDrawableResource(R.drawable.background)

        initObservers()

        Handler(Looper.getMainLooper()).postDelayed({
            startViewModel.signIn()
        }, DELAY_IM_MILLIS)
    }

    private fun initObservers() {
        startViewModel.isSignIn.observe(this, EventObserver(::signInAction))
    }

    private fun signInAction(isSignInSuccess: Boolean) {
        if (isSignInSuccess) {
            navigateToMainScreen()
        } else {
            navigateToAuthorisationScreen()
        }
    }

    private fun navigateToMainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }

    private fun navigateToAuthorisationScreen() {
        val intent = Intent(this, AuthorisationActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }

    companion object {
        private const val DELAY_IM_MILLIS = 1000L
    }
}