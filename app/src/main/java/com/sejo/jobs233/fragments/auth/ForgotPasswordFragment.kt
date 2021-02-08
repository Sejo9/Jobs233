package com.sejo.jobs233.fragments.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sejo.jobs233.databinding.ForgotPasswordFragmentBinding
import com.sejo.jobs233.viewmodels.auth.ForgotPasswordViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ForgotPasswordFragment : Fragment() {

    private var _binding: ForgotPasswordFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ForgotPasswordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(ForgotPasswordViewModel::class.java)

        _binding = ForgotPasswordFragmentBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.resetPasswordBtn.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                viewModel.resetPassword(binding.resetEmailLayout.editText?.text.toString())
            }
        }

        viewModel.emailIsBlank.observe(viewLifecycleOwner, {
            binding.resetEmailLayout.error = "Email Required!"
        })

        viewModel.linkSent.observe(viewLifecycleOwner, {

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}