package link.epoczta.carbscalc.ui.settings

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.preference.PreferenceFragmentCompat
import link.epoczta.carbscalc.R
import link.epoczta.carbscalc.ui.calculations.CalculationsViewModel
import link.epoczta.carbscalc.utils.CarbUnits

class SettingsFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    private val viewModel: CalculationsViewModel by activityViewModels()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.main_preferences, rootKey)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
        if (key == getString(R.string.carbohydrates_unit_key)) {
            viewModel.carbsUnitChanged(
                when (sharedPreferences.getString(key, "")) {
                    getString(R.string.carbohydrate_units_value) -> CarbUnits.CARBOHYDRATE_UNITS
                    else -> CarbUnits.GRAMS
                }
            )
        }
    }

    override fun onResume() {
        super.onResume()
        preferenceManager.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceManager.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

}