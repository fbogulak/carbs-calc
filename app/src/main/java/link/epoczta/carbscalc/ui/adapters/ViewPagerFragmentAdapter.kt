package link.epoczta.carbscalc.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import link.epoczta.carbscalc.ui.calculations.portioncarbs.PortionCarbsFragment
import link.epoczta.carbscalc.ui.calculations.portionweight.PortionWeightFragment

class ViewPagerFragmentAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PortionCarbsFragment()
            else -> PortionWeightFragment()
        }
    }


}