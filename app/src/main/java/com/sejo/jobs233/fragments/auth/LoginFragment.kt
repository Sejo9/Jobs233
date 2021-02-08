package com.sejo.jobs233.fragments.auth

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.sejo.jobs233.R
import com.sejo.jobs233.activities.MainActivity
import com.sejo.jobs233.databinding.FragmentLoginBinding
import com.sejo.jobs233.viewmodels.auth.LoginViewModel
import com.sejo.jobs233.viewmodels.factories.LoginViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: LoginViewModel
    private lateinit var viewModelFactory: LoginViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        viewModelFactory = LoginViewModelFactory(activity?.applicationContext!!)
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        // Inflate the layout for this fragment
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            validate()
        }

        binding.forgotPassword.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_forgotPasswordFragment)
        )

        binding.signupLabel.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_PIFragment)
        )

        viewModel.emailIsEmpty.observe(viewLifecycleOwner, {
            if (it) {
                binding.identityLayout.error = "Email / Username Required!"
                binding.identityLayout.editText?.requestFocus()
                Handler().postDelayed({
                    binding.identityLayout.error = ""
                }, 3000L)
            }
        })

        viewModel.passwordIsEmpty.observe(viewLifecycleOwner, {
            if (it) {
                binding.passwordLayout.error = "Password Required!"
                binding.passwordLayout.editText?.requestFocus()
                Handler().postDelayed({
                    binding.passwordLayout.error = ""
                }, 3000L)
            }
        })

        viewModel.credentialsError.observe(viewLifecycleOwner, {
            if (!it) {
                val identity = binding.identityLayout.editText?.text.toString()
                val password = binding.passwordLayout.editText?.text.toString()
                CoroutineScope(Dispatchers.Main).launch {
                    viewModel.requestToken(identity, password)
                }

            }
        })

        viewModel.loginErrorCode.observe(viewLifecycleOwner, {
            when (it) {
                401 -> {
                    binding.loginButton.visibility = View.VISIBLE
                    binding.loginButton.isEnabled = true
                    binding.loginProgress.visibility = View.INVISIBLE
                    Toast.makeText(activity?.applicationContext, "Unauthorized", Toast.LENGTH_SHORT)
                        .show()
                }
                422 -> {
                    binding.loginButton.visibility = View.VISIBLE
                    binding.loginButton.isEnabled = true
                    binding.loginProgress.visibility = View.INVISIBLE
                    Toast.makeText(
                        activity?.applicationContext,
                        "Invalid login credentials",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    binding.loginButton.visibility = View.VISIBLE
                    binding.loginButton.isEnabled = true
                    binding.loginProgress.visibility = View.INVISIBLE
                    Toast.makeText(
                        activity?.applicationContext,
                        "Server connection error!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        })

        viewModel.loginComplete.observe(viewLifecycleOwner, {
            if (it) {
                Toast.makeText(
                    activity?.applicationContext,
                    "User login successful",
                    Toast.LENGTH_SHORT
                ).show()
                startActivity(Intent(activity, MainActivity::class.java))
                activity?.finish()
            }
        })
    }


    //Checks if all fields are not empty
    private fun validate() {
        binding.loginButton.visibility = View.INVISIBLE
        binding.loginButton.isEnabled = false
        binding.loginProgress.visibility = View.VISIBLE

        val identity = binding.identityLayout.editText?.text.toString()
        val password = binding.passwordLayout.editText?.text.toString()

        viewModel.validateCredentials(identity, password)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}