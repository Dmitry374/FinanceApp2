package com.dima.financeapp.ui.main.profile

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.dima.financeapp.R
import com.dima.financeapp.common.Constants
import com.dima.financeapp.model.domain.User
import com.dima.financeapp.network.request.UserEditRequest
import com.dima.financeapp.ui.main.communication.MainFragmentCommunicationInterface
import com.dima.financeapp.ui.main.communication.ProfileFragmentCommunication
import kotlinx.android.synthetic.main.fragment_add_bill.*
import kotlinx.android.synthetic.main.fragment_profile.*
import java.util.Calendar

class ProfileFragment : Fragment(), ProfileFragmentCommunication {

    private var user: User? = null

    var dateOfBirth: String = Constants.EMPTY_STRING
    var gender: String = Constants.EMPTY_STRING

    override fun setUser(user: User) {
        this.user = user
    }

    private var mainFragmentCommunicationInterface: MainFragmentCommunicationInterface? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is MainFragmentCommunicationInterface) {
            mainFragmentCommunicationInterface = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar(view)

        user?.let { initViews(it) }
    }

    private fun initToolbar(view: View) {
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = resources.getString(R.string.nav_profile)

        toolbar.inflateMenu(R.menu.profile_menu)

        toolbar.setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.apply_user_settings -> {
                    user?.let { user ->
                        val name = edNameProfile.text.toString()
                        val surname = edSurnameProfile.text.toString()
                        if (name.isNotEmpty() && surname.isNotEmpty()) {
                            val userEditRequest = UserEditRequest(
                                id = user.id,
                                name = name,
                                surname = surname,
                                email = user.email,
                                photoUrl = user.photoUrl,
                                datebirth = dateOfBirth,
                                gender = gender
                            )

                            mainFragmentCommunicationInterface?.editUser(userEditRequest)
                        }
                    }
                    return@OnMenuItemClickListener true
                }
                else -> {
                    return@OnMenuItemClickListener true
                }
            }
        })
    }

    private fun initViews(user: User) {

        edNameProfile.setText(user.name)
        edSurnameProfile.setText(user.surname)
        edEmailProfile.setText(user.email)

        if (user.datebirth == Constants.EMPTY_STRING) {
            btnSetDateOfBirthProfile.text = getText(R.string.text_empty)
        } else {
            btnSetDateOfBirthProfile.text = user.datebirth
        }

        dateOfBirth = btnSetDateOfBirthProfile.text.toString()

        if (user.photoUrl.isEmpty()) {
            Glide.with(imgUserProfile)
                .load(R.drawable.profile_img)
                .circleCrop()
                .into(imgUserProfile)
        } else {
            Glide.with(imgUserProfile)
                .load(user.photoUrl)
                .circleCrop()
                .into(imgUserProfile)
        }

        if (user.datebirth.isEmpty()) {
            btnSetDateOfBirthProfile.text = getText(R.string.text_empty)
        } else {
            btnSetDateOfBirthProfile.text = user.datebirth
        }

        // Массив пол
        val listGender = resources.getStringArray(R.array.select_gender_list)

        // адаптер
        val adapter = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, listGender)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerGenderProfile.adapter = adapter

        spinnerGenderProfile.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                gender = listGender[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // do nothing
            }
        }

        // Spinner по умолчанию
        if (user.gender.isEmpty()) {
            spinnerGenderProfile.setSelection(0)
        } else {
            when (user.gender) {
                getText(R.string.text_male) -> spinnerGenderProfile.setSelection(1)
                getText(R.string.text_female) -> spinnerGenderProfile.setSelection(2)
            }
        }

        // Выход из аккаунта
        btnExitProfile.setOnClickListener {

        }

        // Set Date Of Birth
        btnSetDateOfBirthProfile.setOnClickListener {
            getDateOfBirth()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getDateOfBirth() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR) - 20
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(requireActivity(), { _, year, monthOfYear, dayOfMonth ->
            btnSetDateOfBirthProfile.text = "$dayOfMonth/${monthOfYear + 1}/$year"
            dateOfBirth = "$dayOfMonth/${monthOfYear + 1}/$year"
        }, year, month, day)
        datePickerDialog.show()
    }
}