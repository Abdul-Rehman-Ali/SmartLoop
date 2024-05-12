package com.example.smartloop.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.smartloop.AboutUs
import com.example.smartloop.CreateNewPassword
import com.example.smartloop.Feedback
import com.example.smartloop.LoginSignUp
import com.example.smartloop.PrivacyPolicy
import com.example.smartloop.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private var _binding: FragmentProfileBinding? = null // Declare binding variable
    private val binding get() = _binding!! // Non-null reference to binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using view binding
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // for change password text
        binding.changePasswordLayout.setOnClickListener {
            val i = Intent(requireContext(), CreateNewPassword::class.java)
            requireContext().startActivity(i)
        }

        // for about us
        binding.aboutUsLayout.setOnClickListener {
            val i = Intent(requireContext(), AboutUs::class.java)
            requireContext().startActivity(i)
        }

        // for privacy policy
        binding.privacyPolicyLayout.setOnClickListener {
            val i = Intent(requireContext(), PrivacyPolicy::class.java)
            requireContext().startActivity(i)
        }

        // for feedback
        binding.feedbackLayout.setOnClickListener {
            val i = Intent(requireContext(), Feedback::class.java)
            requireContext().startActivity(i)
        }


        // logout
        binding.logoutLayout.setOnClickListener {
            auth.signOut()

            val i = Intent(requireContext(), LoginSignUp::class.java)
            requireContext().startActivity(i)

            Toast.makeText(requireContext(), "Log Out Successfully",  Toast.LENGTH_SHORT).show()
            requireActivity().finish()
        }





    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Destroy the binding when the fragment is destroyed
        _binding = null
    }
}
