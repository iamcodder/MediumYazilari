package com.patronusstudio.preferenceexample

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat

class SettingsFragment : PreferenceFragmentCompat() {

    private val LIGHT_THEME = "Light Theme"
    private val DARK_THEME = "Dark Theme"
    private val PATRONUS_WEB = "http://www.patronusstudio.com/"

    private var isLightMode = true

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        val lightTheme = findPreference<SwitchPreferenceCompat>("appThemeLight")!!
        val darkTheme = findPreference<SwitchPreferenceCompat>("appThemeDark")!!


        lightTheme.setOnPreferenceChangeListener { _, newValue ->
            val currentSwitch = newValue == true
            setSwitchButton(currentSwitch, lightTheme, darkTheme)
            setTheme()
            true
        }

        darkTheme.setOnPreferenceChangeListener { _, newValue ->
            val currentSwitch = newValue == true
            setSwitchButton(currentSwitch, darkTheme, lightTheme)
            setTheme()
            true
        }

        val webPreference = findPreference<Preference>("webPage")
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(PATRONUS_WEB)
        webPreference!!.intent = intent

    }

    private fun setSwitchButton(
        currentSwitch: Boolean,
        currentTheme: SwitchPreferenceCompat,
        otherTheme: SwitchPreferenceCompat
    ) {
        if (currentSwitch) {
            currentTheme.isChecked = true
            otherTheme.isChecked = false
            isLightMode = currentTheme.toString() == DARK_THEME
        } else {
            currentTheme.isChecked = false
            otherTheme.isChecked = true
            isLightMode = currentTheme.toString() == LIGHT_THEME
        }

    }

    private fun setTheme() {
        if (isLightMode) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    }
}