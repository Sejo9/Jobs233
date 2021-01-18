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
import com.sejo.jobs233.viewmodels.auth.SignUpViewModel
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpFragment : Fragment() {

    private lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        login_label.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_signUpFragment_to_loginFragment)
        )

        register_button.setOnClickListener {

            viewModel.setPreference(acc_type_group.checkedRadioButtonId)

            register_button.visibility = View.INVISIBLE
            register_button.isEnabled = false

            reg_progress.visibility = View.VISIBLE

            CoroutineScope(Dispatchers.Main).launch {
                viewModel.register()
            }
        }

        viewModel.registerResponse.observe(viewLifecycleOwner, Observer {
            if (it.success) {
                register_button.visibility = View.VISIBLE
                register_button.isEnabled = true
                reg_progress.visibility = View.INVISIBLE

                Toast.makeText(activity?.applicationContext, it.message, Toast.LENGTH_SHORT).show()
                view.findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
            } else {
                register_button.visibility = View.VISIBLE
                register_button.isEnabled = true
                reg_progress.visibility = View.INVISIBLE

                Toast.makeText(
                    activity?.applicationContext,
                    "Registration unsuccessful",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

}