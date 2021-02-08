package com.sejo.jobs233.fragments.inbox

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sejo.jobs233.databinding.FragmentNotificationsBinding
import com.sejo.jobs233.network.checkNetworkConnection

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (checkNetworkConnection(activity?.applicationContext!!)) {
            Toast.makeText(activity?.applicationContext, "Network Available", Toast.LENGTH_SHORT)
                .show()
        } else {
            binding.notifyNoNetLayout.visibility = View.VISIBLE
        }

        binding.notifRetyBtn.setOnClickListener {
            if (checkNetworkConnection(activity?.applicationContext!!)) {
                binding.notifyNoNetLayout.visibility = View.GONE
                binding.notificationsRecycler.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}