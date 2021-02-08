package com.sejo.jobs233.fragments.auth.signup

import android.os.Bundle
import android.os.Handler
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.sejo.jobs233.R
import com.sejo.jobs233.activities.AuthActivity
import com.sejo.jobs233.databinding.FragmentAIBinding

class AIFragment : Fragment() {

    private var _binding: FragmentAIBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAIBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.aiNextBtn.setOnClickListener {
            if (validateInfo()) {
                it.findNavController().navigate(R.id.action_AIFragment_to_signUpFragment)
            }
        }

        binding.aiLoginLabel.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_AIFragment_to_loginFragment))

    }

    private fun validateInfo(): Boolean {

        var error = false

        val username: String = binding.usernameLayout.editText?.text.toString().trim()
        val email: String = binding.signEmailLayout.editText?.text.toString().trim()
        val password: String = binding.signPasswordLayout.editText?.text.toString().trim()

        if (username.isEmpty()) {
            binding.usernameLayout.error = "Username Required!"
            binding.usernameLayout.editText?.requestFocus()
            error = true
            Handler().postDelayed({
                binding.usernameLayout.error = ""
            }, 3000L)
        }

        if (email.isEmpty()) {
            binding.signEmailLayout.error = "Email Required!"
            binding.signEmailLayout.editText?.requestFocus()
            error = true
            Handler().postDelayed({
                binding.signEmailLayout.error = ""
            }, 3000L)
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.signEmailLayout.error = "Invalid Email!"
            binding.signEmailLayout.editText?.requestFocus()
            error = true
            Handler().postDelayed({
                binding.signEmailLayout.error = ""
            }, 3000L)
        }

        if (password.isEmpty()) {
            binding.signPasswordLayout.error = "Password Required!"
            binding.signPasswordLayout.editText?.requestFocus()
            error = true
            Handler().postDelayed({
                binding.signPasswordLayout.error = ""
            }, 3000L)
        } else if (password.length < 8) {
            binding.signPasswordLayout.error = "Password is too short!"
            binding.signPasswordLayout.editText?.requestFocus()
            error = true
            Handler().postDelayed({
                binding.signPasswordLayout.error = ""
            }, 3000L)
        }

        return if (error) {
            false
        } else {
            setAccountInfo(username, email, password)

            true
        }
    }

    private fun setAccountInfo(username: String, email: String, password: String) {
        AuthActivity.username = username
        AuthActivity.email = email
        AuthActivity.password = password
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}