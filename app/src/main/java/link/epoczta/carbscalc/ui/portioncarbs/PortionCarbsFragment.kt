package link.epoczta.carbscalc.ui.portioncarbs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import link.epoczta.carbscalc.R
import link.epoczta.carbscalc.databinding.FragmentPortionCarbsBinding

class PortionCarbsFragment : Fragment() {

    private lateinit var binding: FragmentPortionCarbsBinding
    private lateinit var viewModel: PortionCarbsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPortionCarbsBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(PortionCarbsViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.portionWeightString.observe(viewLifecycleOwner) {
            viewModel.calculatePortionCarbs()
        }

        viewModel.carbsIn100gString.observe(viewLifecycleOwner) {
            viewModel.calculatePortionCarbs()
        }

        return binding.root
    }
}