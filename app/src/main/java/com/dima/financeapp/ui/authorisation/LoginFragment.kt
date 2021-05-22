package com.dima.financeapp.ui.authorisation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dima.financeapp.App
import com.dima.financeapp.R
import com.dima.financeapp.ui.authorisation.communication.FragmentAuthorisationCommunication
import com.dima.financeapp.ui.authorisation.viewmodel.AuthorisationViewModel
import com.dima.financeapp.utils.EventObserver
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val authorisationViewModel: AuthorisationViewModel by viewModels {
        viewModelFactory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity

        Glide.with(imgLogoSignIn)
            .load(R.drawable.logo)
            .into(imgLogoSignIn)

        authorisationViewModel.loginSuccess.observe(
            viewLifecycleOwner,
            EventObserver { isLoginSuccess ->
                if (isLoginSuccess) {
                    edEmailSignIn.text.clear()
                    edPasswordSignIn.text.clear()

                    if (activity is FragmentAuthorisationCommunication) {
                        activity.goToMainScreen()
                    }
                } else {
                    Toast.makeText(activity, R.string.input_incorrect_login_data, Toast.LENGTH_SHORT).show()
                }
            })

        btnSignIn.setOnClickListener {
            val email = edEmailSignIn.text.toString()
            val password = edPasswordSignIn.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(activity, R.string.fill_in_all_fields, Toast.LENGTH_SHORT).show()
            } else {
                authorisationViewModel.loginUser(email, password)
            }
        }
    }
}