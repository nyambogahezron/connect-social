package com.hezron.connect.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hezron.connect.R
import com.hezron.connect.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnStudent.setOnClickListener {
            // navigate to login for now, or specific flow
            findNavController().navigate(R.id.action_welcome_to_login)
        }
        
        binding.btnTeacher.setOnClickListener {
             findNavController().navigate(R.id.action_welcome_to_login)
        }
        
        binding.btnInstitute.setOnClickListener {
             findNavController().navigate(R.id.action_welcome_to_login)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
