package com.hezron.connect.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hezron.connect.MainActivity
import com.hezron.connect.R
import com.hezron.connect.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            // Perform login logic
            // On success:
            startActivity(Intent(requireContext(), MainActivity::class.java))
            requireActivity().finish()
        }

        binding.tvSignup.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_signup)
        }
        
        binding.tvForgotPassword.setOnClickListener {
            // navigate to forgot password
            // findNavController().navigate(R.id.action_login_to_forgot_password)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
