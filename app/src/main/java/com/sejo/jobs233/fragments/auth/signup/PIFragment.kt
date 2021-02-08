package com.sejo.jobs233.fragments.auth.signup

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.sejo.jobs233.R
import com.sejo.jobs233.activities.AuthActivity
import com.sejo.jobs233.databinding.FragmentPIBinding

class PIFragment : Fragment() {

    private var _binding: FragmentPIBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPIBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.piNextBtn.setOnClickListener {
            if (validateInfo()) {
                it.findNavController().navigate(R.id.action_PIFragment_to_AIFragment)
            }
        }
        binding.piLoginLabel.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_PIFragment_to_loginFragment))

    }

    private fun validateInfo(): Boolean {

        var error = false

        val firstName = binding.firstNameLayout.editText?.text.toString().trim()
        val lastName = binding.lastNameLayout.editText?.text.toString().trim()

        if (firstName.isEmpty()) {
            binding.firstNameLayout.error = "First Name Required!"
            binding.firstNameLayout.editText?.requestFocus()
            error = true
            Handler().postDelayed({
                binding.firstNameLayout.error = ""
            }, 3000L)
        }

        if (lastName.isEmpty()) {
            binding.lastNameLayout.error = "Last Name Required!"
            binding.lastNameLayout.editText?.requestFocus()
            error = true
            Handler().postDelayed({
                binding.lastNameLayout.error = ""
            }, 3000L)
        }

        return if (error) {
            false
        } else {
            setPersonalInfo(firstName, lastName)

            true
        }
    }

    private fun setPersonalInfo(firstName: String, lastName: String) {
        AuthActivity.firstName = firstName
        AuthActivity.lastName = lastName
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}