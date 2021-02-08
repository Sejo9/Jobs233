package com.sejo.jobs233.fragments.auth.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.sejo.jobs233.R
import com.sejo.jobs233.databinding.FragmentSignUpBinding
import com.sejo.jobs233.viewmodels.auth.SignUpViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpFragment : Fragment() {

    private lateinit var viewModel: SignUpViewModel

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

        _binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginLabel.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_signUpFragment_to_loginFragment)
        )

        binding.registerButton.setOnClickListener {

            viewModel.setPreference(binding.accTypeGroup.checkedRadioButtonId)

            binding.registerButton.visibility = View.INVISIBLE
            binding.registerButton.isEnabled = false

            binding.regProgress.visibility = View.VISIBLE

            CoroutineScope(Dispatchers.Main).launch {
                viewModel.register()
            }
        }

        viewModel.registerResponse.observe(viewLifecycleOwner, Observer {
            if (it.success) {
                binding.registerButton.visibility = View.VISIBLE
                binding.registerButton.isEnabled = true
                binding.regProgress.visibility = View.INVISIBLE

                Toast.makeText(activity?.applicationContext, it.message, Toast.LENGTH_SHORT).show()
                view.findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
            } else {
                binding.registerButton.visibility = View.VISIBLE
                binding.registerButton.isEnabled = true
                binding.regProgress.visibility = View.INVISIBLE

                Toast.makeText(
                    activity?.applicationContext,
                    "Registration unsuccessful",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}