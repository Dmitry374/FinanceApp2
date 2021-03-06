package com.dima.financeapp.sharedpreference

import android.content.Context
import android.content.SharedPreferences
import com.dima.financeapp.R
import com.dima.financeapp.common.Constants

class SharedPreferenceHelper(private val sharedPreferences: SharedPreferences) {

    fun clearAllPrefs(context: Context){
        context.getSharedPreferences(context.resources.getString(R.string.app_name), Context.MODE_PRIVATE).edit().clear().apply()
    }

    fun clearIsSignValue() = sharedPreferences.edit().putBoolean(Constants.IS_SIGN, false).apply()
    fun setSignInAccount(isSign: Boolean) = sharedPreferences.edit().putBoolean(Constants.IS_SIGN, isSign).apply()
    fun getSignInAccount(): Boolean = sharedPreferences.getBoolean(Constants.IS_SIGN, false)

    fun setUserEmail(email: String) = sharedPreferences.edit().putString(Constants.USER_EMAIL, email).apply()
    fun getUserEmail(): String = sharedPreferences.getString(Constants.USER_EMAIL, "") ?: ""
}