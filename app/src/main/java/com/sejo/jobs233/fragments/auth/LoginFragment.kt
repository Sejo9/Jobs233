package com.sejo.jobs233.fragments.auth

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.sejo.jobs233.R
import com.sejo.jobs233.activities.MainActivity
import com.sejo.jobs233.viewmodels.auth.LoginViewModel
import com.sejo.jobs233.viewmodels.factories.LoginViewModelFactory
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var viewModelFactory: LoginViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModelFactory = LoginViewModelFactory(activity?.applicationContext!!)
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        login_button.setOnClickListener {
            validate()
        }

        forgot_password.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_forgotPasswordFragment)
        )

        signup_label.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_PIFragment)
        )

        viewModel.emailIsEmpty.observe(viewLifecycleOwner, Observer {
            if (it) {
                identity_layout.error = "Email / Username Required!"
                identity_layout.editText?.requestFocus()
                Handler().postDelayed({
                    identity_layout.error = ""
                }, 3000L)
            }
        })

        viewModel.passwordIsEmpty.observe(viewLifecycleOwner, Observer {
            if (it) {
                password_layout.error = "Password Required!"
                password_layout.editText?.requestFocus()
                Handler().postDelayed({
                    password_layout.error = ""
                }, 3000L)
            }
        })

        viewModel.loginError.observe(viewLifecycleOwner, Observer {
            if (!it) {
                val identity = identity_layout.editText?.text.toString()
                val password = password_layout.editText?.text.toString()
                CoroutineScope(Dispatchers.Main).launch {
                    viewModel.login(identity, password)
                }

            }
        })

        viewModel.loginErrorCode.observe(viewLifecycleOwner, Observer {
            when (it) {
                402 -> {
                    login_button.visibility = View.VISIBLE
                    login_button.isEnabled = true
                    login_progress.visibility = View.INVISIBLE
                    Toast.makeText(activity?.applicationContext, "Unauthorized", Toast.LENGTH_SHORT)
                        .show()
                }
                422 -> {
                    login_button.visibility = View.VISIBLE
                    login_button.isEnabled = true
                    login_progress.visibility = View.INVISIBLE
                    Toast.makeText(
                        activity?.applicationContext,
                        "Invalid login credentials",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    login_button.visibility = View.VISIBLE
                    login_button.isEnabled = true
                    login_progress.visibility = View.INVISIBLE
                    Toast.makeText(
                        activity?.applicationContext,
                        "Server connection error!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        })

        viewModel.loginComplete.observe(viewLifecycleOwner, Observer {
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
        login_button.visibility = View.INVISIBLE
        login_button.isEnabled = false
        login_progress.visibility = View.VISIBLE

        val identity = identity_layout.editText?.text.toString()
        val password = password_layout.editText?.text.toString()

        viewModel.validateCredentials(identity, password)

    }

}