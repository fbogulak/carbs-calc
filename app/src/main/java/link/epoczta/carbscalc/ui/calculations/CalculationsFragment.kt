package link.epoczta.carbscalc.ui.calculations

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.preference.PreferenceManager
import com.google.android.material.tabs.TabLayoutMediator
import link.epoczta.carbscalc.R
import link.epoczta.carbscalc.databinding.FragmentCalculationsBinding
import link.epoczta.carbscalc.ui.adapters.ViewPagerFragmentAdapter
import link.epoczta.carbscalc.utils.CarbUnits

class CalculationsFragment : Fragment() {

    private lateinit var binding: FragmentCalculationsBinding
    private val viewModel: CalculationsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalculationsBinding.inflate(inflater)

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

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
        val unitValue = sharedPreferences.getString("carbohydrates_unit", "")
        viewModel.selectedUnit.value = when (unitValue) {
            getString(R.string.carbohydrate_units_value) -> CarbUnits.CARBOHYDRATE_UNITS
            else -> CarbUnits.GRAMS
        }

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