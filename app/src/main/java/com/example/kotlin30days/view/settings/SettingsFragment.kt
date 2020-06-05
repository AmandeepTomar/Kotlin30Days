package com.example.kotlin30days.view.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.kotlin30days.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}