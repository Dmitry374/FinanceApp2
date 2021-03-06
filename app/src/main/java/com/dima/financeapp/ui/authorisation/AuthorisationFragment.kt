package com.dima.financeapp.ui.authorisation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.dima.financeapp.ui.authorisation.communication.FragmentAuthorisationCommunication
import com.dima.financeapp.R
import kotlinx.android.synthetic.main.fragment_authorisation.*

class AuthorisationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_authorisation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity

        Glide.with(imgLogoAuthorisation)
            .load(R.drawable.logo)
            .into(imgLogoAuthorisation)

        btnSignInAccount.setOnClickListener {
            if (activity is FragmentAuthorisationCommunication) {
                activity.onOpenLoginScreen()
            }
        }

        btnRegisterAccount.setOnClickListener {
            if (activity is FragmentAuthorisationCommunication) {
                activity.onOpenRegistrationScreen()
            }
        }
    }
}