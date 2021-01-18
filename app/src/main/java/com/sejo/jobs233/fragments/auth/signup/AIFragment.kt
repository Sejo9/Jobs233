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
import kotlinx.android.synthetic.main.fragment_a_i.*

class AIFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_a_i, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ai_next_btn.setOnClickListener {
            if (validateInfo()) {
                it.findNavController().navigate(R.id.action_AIFragment_to_signUpFragment)
            }
        }

        ai_login_label.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_AIFragment_to_loginFragment))

    }

    private fun validateInfo(): Boolean {

        var error = false

        val username: String = username_layout.editText?.text.toString().trim()
        val email: String = sign_email_layout.editText?.text.toString().trim()
        val password: String = sign_password_layout.editText?.text.toString().trim()

        if (username.isEmpty()) {
            username_layout.error = "Username Required!"
            username_layout.editText?.requestFocus()
            error = true
            Handler().postDelayed({
                username_layout.error = ""
            }, 3000L)
        }

        if (email.isEmpty()) {
            sign_email_layout.error = "Email Required!"
            sign_email_layout.editText?.requestFocus()
            error = true
            Handler().postDelayed({
                sign_email_layout.error = ""
            }, 3000L)
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            sign_email_layout.error = "Invalid Email!"
            sign_email_layout.editText?.requestFocus()
            error = true
            Handler().postDelayed({
                sign_email_layout.error = ""
            }, 3000L)
        }

        if (password.isEmpty()) {
            sign_password_layout.error = "Password Required!"
            sign_password_layout.editText?.requestFocus()
            error = true
            Handler().postDelayed({
                sign_password_layout.error = ""
            }, 3000L)
        } else if (password.length < 8) {
            sign_password_layout.error = "Password is too short!"
            sign_password_layout.editText?.requestFocus()
            error = true
            Handler().postDelayed({
                sign_password_layout.error = ""
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
}