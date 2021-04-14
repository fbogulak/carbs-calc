package link.epoczta.carbscalc

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import link.epoczta.carbscalc.utils.toDoubleOrZero

class MainViewModel : ViewModel() {

    val portionWeightString = MutableLiveData<String>()
    val carbsIn100gString = MutableLiveData<String>()
    val carbsInPortionString = MutableLiveData<String>()

    private val carbsInPortion = MutableLiveData(0.0)
    val carbsInPortionResult = Transformations.map(carbsInPortion) {
        PORTION_CARBS_FORMAT.format(it)
    }

    private val portionWeight = MutableLiveData(0.0)
    val portionWeightResult = Transformations.map(portionWeight) {
        PORTION_WEIGHT_FORMAT.format(it)
    }

    fun calculatePortionCarbs() {
        val portionWeight = portionWeightString.value.toDoubleOrZero()
        val carbsIn100g = carbsIn100gString.value.toDoubleOrZero()
        carbsInPortion.value = if (portionWeight > 0 && carbsIn100g > 0) {
            portionWeight * carbsIn100g / 100
        } else {
            0.0
        }
    }

    fun calculatePortionWeight() {
        val carbsInPortion = carbsInPortionString.value.toDoubleOrZero()
        val carbsIn100g = carbsIn100gString.value.toDoubleOrZero()
        portionWeight.value = if (carbsInPortion > 0 && carbsIn100g > 0) {
            carbsInPortion / carbsIn100g * 100
        } else {
            0.0
        }
    }

    companion object{
        const val PORTION_CARBS_FORMAT = "%.2f"
        const val PORTION_WEIGHT_FORMAT = "%.0f"
    }
}