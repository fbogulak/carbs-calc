package link.epoczta.carbscalc.ui.calculations

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import link.epoczta.carbscalc.utils.CarbUnits
import link.epoczta.carbscalc.utils.toDoubleOrZero

class CalculationsViewModel : ViewModel() {

    val portionWeightString = MutableLiveData<String>()
    val carbsIn100gString = MutableLiveData<String>()
    val carbsInPortionString = MutableLiveData<String>()

    val selectedUnit = MutableLiveData<CarbUnits>()

    fun calculatePortionCarbs() {
        val portionWeight = portionWeightString.value.toDoubleOrZero()
        val carbsIn100g = carbsIn100gString.value.toDoubleOrZero()

        val carbsInPortion = if (carbsIn100g in 0.0..100.0) {
            var result = portionWeight * carbsIn100g / 100
            if (selectedUnit.value == CarbUnits.CARBOHYDRATE_UNITS)
                result /= 10
            result
        } else {
            0.0
        }
        carbsInPortionString.value = PORTION_CARBS_FORMAT.format(carbsInPortion)
    }

    fun calculatePortionWeight() {
        val carbsInPortion = carbsInPortionString.value.toDoubleOrZero()
        val carbsIn100g = carbsIn100gString.value.toDoubleOrZero()

        val portionWeight = if (carbsIn100g > 0 && carbsIn100g <= 100) {
            var result = carbsInPortion / carbsIn100g * 100
            if (selectedUnit.value == CarbUnits.CARBOHYDRATE_UNITS)
                result *= 10
            result
        } else {
            0.0
        }
        portionWeightString.value = PORTION_WEIGHT_FORMAT.format(portionWeight)
    }

    companion object {
        const val PORTION_CARBS_FORMAT = "%.2f"
        const val PORTION_WEIGHT_FORMAT = "%.0f"
    }
}