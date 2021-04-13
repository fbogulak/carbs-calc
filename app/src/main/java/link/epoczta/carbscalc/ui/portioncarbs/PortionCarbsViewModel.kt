package link.epoczta.carbscalc.ui.portioncarbs

import androidx.lifecycle.*
import link.epoczta.carbscalc.utils.toDoubleOrZero

class PortionCarbsViewModel : ViewModel() {

    val portionWeightString = MutableLiveData<String>()

    val carbsIn100gString = MutableLiveData<String>()

    private val carbsInPortion = MutableLiveData(0.0)
    val carbsInPortionString = Transformations.map(carbsInPortion) {
        "%.2f".format(it)
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
}