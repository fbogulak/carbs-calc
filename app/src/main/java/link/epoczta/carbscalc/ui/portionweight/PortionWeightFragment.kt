package link.epoczta.carbscalc.ui.portionweight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import link.epoczta.carbscalc.R
import link.epoczta.carbscalc.databinding.FragmentPortionCarbsBinding
import link.epoczta.carbscalc.databinding.FragmentPortionWeightBinding
import link.epoczta.carbscalc.ui.portioncarbs.PortionCarbsViewModel

class PortionWeightFragment : Fragment() {

    private lateinit var binding: FragmentPortionWeightBinding
    private lateinit var viewModel: PortionWeightViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        binding = FragmentPortionWeightBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(PortionWeightViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }
}