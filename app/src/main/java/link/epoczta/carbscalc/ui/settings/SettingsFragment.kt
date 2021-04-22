package link.epoczta.carbscalc.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import link.epoczta.carbscalc.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.main_preferences, rootKey)
    }
}