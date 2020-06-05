package com.example.kotlin30days.utility

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager


class AppPref(val applicationContext: Context) {

    fun setLogin(isDebuggable: Boolean) {
        val sharedPreference: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(applicationContext)
        sharedPreference.edit().putBoolean(IS_USER_LOGIN, isDebuggable).apply()
    }

    fun isLogin(): Boolean {
        applicationContext.let {
            val sharedPreference: SharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(it)
            return sharedPreference.getBoolean(IS_USER_LOGIN, false)
        }
    }
}