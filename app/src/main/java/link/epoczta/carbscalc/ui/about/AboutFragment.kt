package link.epoczta.carbscalc.ui.about

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import link.epoczta.carbscalc.databinding.FragmentAboutBinding
import link.epoczta.carbscalc.databinding.FragmentCalculationsBinding

class AboutFragment : Fragment() {

    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(inflater)

        binding.icons8LinkText.movementMethod = LinkMovementMethod.getInstance()

        return binding.root
    }
}