package com.gamest.fastvideoplayer.mainUI.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.gamest.fastvideoplayer.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }


}