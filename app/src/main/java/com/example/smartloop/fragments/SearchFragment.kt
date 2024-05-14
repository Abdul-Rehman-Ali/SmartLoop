package com.example.smartloop.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.smartloop.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.phoneCall1.setOnClickListener {
            val number = "+923181646340"
            val intent = Intent(Intent.ACTION_DIAL)
            intent.setData(Uri.parse("tel:$number"))
            startActivity(intent)
        }

        binding.phoneCall2.setOnClickListener {
            val number = "+923181646340"
            val intent = Intent(Intent.ACTION_DIAL)
            intent.setData(Uri.parse("tel:$number"))
            startActivity(intent)
        }

        binding.phoneCall3.setOnClickListener {
            val number = "+923181646340"
            val intent = Intent(Intent.ACTION_DIAL)
            intent.setData(Uri.parse("tel:$number"))
            startActivity(intent)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Clean up reference to binding to avoid memory leaks
    }
}
