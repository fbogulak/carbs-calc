package link.epoczta.carbscalc.ui.calculations.portionweight

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import link.epoczta.carbscalc.ui.calculations.CalculationsViewModel
import link.epoczta.carbscalc.R
import link.epoczta.carbscalc.databinding.FragmentPortionWeightBinding
import link.epoczta.carbscalc.utils.CarbUnits
import link.epoczta.carbscalc.utils.DecimalDigitsInputFilter
import java.lang.NumberFormatException
import java.text.DecimalFormatSymbols

class PortionWeightFragment : Fragment(), TextWatcher {

    private lateinit var binding: FragmentPortionWeightBinding
    private val viewModel: CalculationsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPortionWeightBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.carbsInPortionEdit.apply {
            filters = arrayOf(DecimalDigitsInputFilter(10, 10))
            addTextChangedListener(this@PortionWeightFragment)
            setSelectAllOnFocus(true)
        }

        binding.carbsIn100gEdit.apply {
            filters = arrayOf(DecimalDigitsInputFilter(10, 10))
            addTextChangedListener(this@PortionWeightFragment)
            setSelectAllOnFocus(true)
        }

        viewModel.selectedUnit.observe(viewLifecycleOwner) {
            it?.let {
                when (it) {
                    CarbUnits.CARBOHYDRATE_UNITS -> {
                        binding.carbsInPortionTextField.suffixText = getString(R.string.carb_units)
                        viewModel.calculatePortionWeight()
                    }
                    CarbUnits.GRAMS -> {
                        binding.carbsInPortionTextField.suffixText = getString(R.string.g)
                        viewModel.calculatePortionWeight()
                    }
                }
            }
        }

        return binding.root
    }


    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (s.hashCode() == binding.carbsIn100gEdit.text.hashCode()) {

            val carbsIn100g: Double

            if (s.isNullOrEmpty()) {
                setError(null, false)
                return
            } else {
                val localSeparator = DecimalFormatSymbols.getInstance().decimalSeparator.toString()
                try {
                    carbsIn100g = s.toString().replace(localSeparator, ".").toDouble()
                } catch (e: NumberFormatException) {
                    setError(getString(R.string.wrong_number_format), false)
                    return
                }
            }

            when {
                carbsIn100g < 0 -> setError(
                    getString(R.string.this_value_cant_be_lower_than_0),
                    false
                )
                carbsIn100g == 0.0 -> setError(getString(R.string.this_value_cant_be_0), false)
                carbsIn100g > 100 -> setError(
                    getString(R.string.this_value_cant_be_greater_than_100),
                    false
                )
                else -> setError(null, true)
            }
        }
    }

    override fun afterTextChanged(s: Editable?) {
        viewModel.calculatePortionWeight()
    }

    private fun setError(error: String?, makeResultVisible: Boolean) {
        binding.carbsIn100gTextField.error = error

        if (makeResultVisible) {
            binding.portionWeightText.visibility = View.VISIBLE
            binding.unitLabelPortion.visibility = View.VISIBLE
        } else {
            binding.portionWeightText.visibility = View.GONE
            binding.unitLabelPortion.visibility = View.GONE
        }
    }
}