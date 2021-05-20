package link.epoczta.carbscalc.ui.calculations

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import link.epoczta.carbscalc.utils.CarbUnits
import link.epoczta.carbscalc.utils.toDoubleOrZero

class CalculationsViewModel : ViewModel() {

    val portionWeightString = MutableLiveData<String>()
    val carbsIn100gString = MutableLiveData<String>()
    val carbsInPortionString = MutableLiveData<String>()

    private val carbsInMeal = MutableLiveData(0.0)
    val carbsInMealString = Transformations.map(carbsInMeal) {
        CARBS_FORMAT.format(it)
    }

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
        carbsInPortionString.value = CARBS_FORMAT.format(carbsInPortion)
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

    fun calculateMealCarbs() {
        val carbsInPortion = carbsInPortionString.value.toDoubleOrZero()
        carbsInMeal.value = carbsInMeal.value?.plus(carbsInPortion)
    }

    fun resetMealCarbs() {
        carbsInMeal.value = 0.0
    }

    fun carbsUnitChanged(newUnit: CarbUnits) {
        when (newUnit) {
            CarbUnits.CARBOHYDRATE_UNITS -> carbsInMeal.value = carbsInMeal.value?.div(10)
            else -> carbsInMeal.value = carbsInMeal.value?.times(10)
        }
    }

    companion object {
        const val CARBS_FORMAT = "%.2f"
        const val PORTION_WEIGHT_FORMAT = "%.0f"
    }
}