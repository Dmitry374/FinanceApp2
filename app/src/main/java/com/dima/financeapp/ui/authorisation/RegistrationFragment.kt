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
import com.dima.financeapp.ui.authorisation.viewmodel.AuthorisationViewModel
import kotlinx.android.synthetic.main.fragment_registration.*
import javax.inject.Inject

class RegistrationFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(imgLogoRegistration)
            .load(R.drawable.logo)
            .into(imgLogoRegistration)

        btnRegister.setOnClickListener {
            val email = edEmailRegister.text.toString()
            val password = edPasswordRegister.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(activity, R.string.fill_in_all_fields, Toast.LENGTH_SHORT).show()
            } else {
                authorisationViewModel.registrationUser(email, password)
            }
        }
    }
}