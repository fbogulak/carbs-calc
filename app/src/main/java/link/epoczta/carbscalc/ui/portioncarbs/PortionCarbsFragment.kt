package link.epoczta.carbscalc.ui.portioncarbs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import link.epoczta.carbscalc.R

class PortionCarbsFragment : Fragment() {

    private lateinit var viewModel: PortionCarbsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewModel =
                ViewModelProvider(this).get(PortionCarbsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_portion_carbs, container, false)

        return root
    }
}