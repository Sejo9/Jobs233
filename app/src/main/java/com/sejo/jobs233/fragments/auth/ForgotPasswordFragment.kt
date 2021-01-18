package com.sejo.jobs233.fragments.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.sejo.jobs233.R
import com.sejo.jobs233.viewmodels.auth.ForgotPasswordViewModel
import kotlinx.android.synthetic.main.forgot_password_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ForgotPasswordFragment : Fragment() {

    private lateinit var viewModel: ForgotPasswordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ForgotPasswordViewModel::class.java)

        return inflater.inflate(R.layout.forgot_password_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reset_password_btn.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                viewModel.resetPassword(reset_email_layout.editText?.text.toString())
            }
        }

        viewModel.emailIsBlank.observe(viewLifecycleOwner, Observer {
            reset_email_layout.error = "Email Required!"
        })

        viewModel.linkSent.observe(viewLifecycleOwner, Observer {
            view.findNavController().navigate(R.id.action_forgotPasswordFragment_to_loginFragment)
        })
    }

}