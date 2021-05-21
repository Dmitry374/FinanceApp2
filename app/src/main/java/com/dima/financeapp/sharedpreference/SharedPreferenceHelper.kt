package com.dima.financeapp.sharedpreference

import android.content.Context
import android.content.SharedPreferences
import com.dima.financeapp.common.Constants

class SharedPreferenceHelper(private val sharedPreferences: SharedPreferences) {

    fun clearAllPrefs(context: Context){
        context.getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().apply()
    }

    fun clearIsSignValue() = sharedPreferences.edit().putBoolean(Constants.IS_SIGN, false).apply()
    fun setSignInAccount(isSign: Boolean) = sharedPreferences.edit().putBoolean(Constants.IS_SIGN, isSign).apply()
    fun getSignInAccount(): Boolean = sharedPreferences.getBoolean(Constants.IS_SIGN, false)
}