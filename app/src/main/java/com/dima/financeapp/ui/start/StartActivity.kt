package com.dima.financeapp.ui.start

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.dima.financeapp.ui.authorisation.AuthorisationActivity
import com.dima.financeapp.R

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        window.setBackgroundDrawableResource(R.drawable.background)

        Handler(Looper.getMainLooper()).postDelayed({
            navigateToAuthorisationScreen()
        }, DELAY_IM_MILLIS)
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