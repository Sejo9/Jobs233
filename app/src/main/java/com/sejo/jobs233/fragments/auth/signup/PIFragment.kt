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
import kotlinx.android.synthetic.main.fragment_p_i.*

class PIFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_p_i, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pi_next_btn.setOnClickListener {
            if (validateInfo()) {
                it.findNavController().navigate(R.id.action_PIFragment_to_AIFragment)
            }
        }
        pi_login_label.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_PIFragment_to_loginFragment))

    }

    private fun validateInfo(): Boolean {

        var error = false

        val firstName = first_name_layout.editText?.text.toString().trim()
        val lastName = last_name_layout.editText?.text.toString().trim()

        if (firstName.isEmpty()) {
            first_name_layout.error = "First Name Required!"
            first_name_layout.editText?.requestFocus()
            error = true
            Handler().postDelayed({
                first_name_layout.error = ""
            }, 3000L)
        }

        if (lastName.isEmpty()) {
            last_name_layout.error = "Last Name Required!"
            last_name_layout.editText?.requestFocus()
            error = true
            Handler().postDelayed({
                last_name_layout.error = ""
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
}