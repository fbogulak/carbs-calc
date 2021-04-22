package link.epoczta.carbscalc.ui.calculations

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
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

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}