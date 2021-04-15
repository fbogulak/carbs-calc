package link.epoczta.carbscalc.ui.portionweight

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import link.epoczta.carbscalc.MainViewModel
import link.epoczta.carbscalc.databinding.FragmentPortionWeightBinding

class PortionWeightFragment : Fragment(), TextWatcher {

    private lateinit var binding: FragmentPortionWeightBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPortionWeightBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.carbsInPortionEdit.addTextChangedListener(this)
        binding.carbsIn100gEdit.addTextChangedListener(this)

        return binding.root
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
        viewModel.calculatePortionWeight()
    }
}