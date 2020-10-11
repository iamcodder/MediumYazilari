package com.patronusstudio.preferenceexample

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        val lightTheme = findPreference<SwitchPreferenceCompat>("appThemeLight")
        val darkTheme = findPreference<SwitchPreferenceCompat>("appThemeDark")

        lightTheme?.setOnPreferenceChangeListener { _, newValue ->
            if (newValue == true) {
                darkTheme?.isChecked = false
                setTheme(false)
            } else {
                darkTheme?.isChecked = true
                setTheme(true)
            }
            true
        }


        darkTheme?.setOnPreferenceChangeListener { _, newValue ->

            if (newValue == true) {
                lightTheme?.isChecked = false
                setTheme(true)
            } else {
                lightTheme?.isChecked = true
                setTheme(false)
            }

            true
        }


    }

    private fun setTheme(isDarkActive: Boolean) {
        if (isDarkActive) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    }
}