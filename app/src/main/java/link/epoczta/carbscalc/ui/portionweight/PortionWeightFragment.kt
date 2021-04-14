package link.epoczta.carbscalc.ui.portionweight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import link.epoczta.carbscalc.MainViewModel
import link.epoczta.carbscalc.databinding.FragmentPortionWeightBinding

class PortionWeightFragment : Fragment() {

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

        return binding.root
    }
}