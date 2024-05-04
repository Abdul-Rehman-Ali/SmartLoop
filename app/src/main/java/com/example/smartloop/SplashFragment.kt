package com.example.smartloop

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.smartloop.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {


    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment using view binding
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        val view = binding.root

        // Handler to navigate after a delay
        Handler().postDelayed({
            if (finishedOnboarding()){
                findNavController().navigate(R.id.action_splashFragment_to_loginSignUp)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_viewPagerFregment)
            }
        }, 3000)
        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        // Release the binding when the fragment's view is destroyed
        _binding = null
    }

    private fun finishedOnboarding(): Boolean {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }

}