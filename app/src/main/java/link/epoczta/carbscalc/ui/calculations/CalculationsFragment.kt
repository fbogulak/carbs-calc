package link.epoczta.carbscalc.ui.calculations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.tabs.TabLayoutMediator
import link.epoczta.carbscalc.R
import link.epoczta.carbscalc.databinding.FragmentCalculationsBinding
import link.epoczta.carbscalc.databinding.FragmentPortionCarbsBinding
import link.epoczta.carbscalc.ui.adapters.ViewPagerFragmentAdapter

class CalculationsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCalculationsBinding.inflate(inflater)

        binding.viewPager.adapter = ViewPagerFragmentAdapter(requireActivity())
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = getString(
                when (position) {
                    0 -> R.string.title_portion_carbs
                    else -> R.string.portion_weight
                }
            )
        }.attach()

        return binding.root
    }
}