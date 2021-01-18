package com.sejo.jobs233.fragments.inbox

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sejo.jobs233.R
import com.sejo.jobs233.network.checkNetworkConnection
import kotlinx.android.synthetic.main.fragment_notifications.*

class NotificationsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (checkNetworkConnection(activity?.applicationContext!!)) {
            Toast.makeText(activity?.applicationContext, "Network Available", Toast.LENGTH_SHORT)
                .show()
        } else {
            notify_no_net_layout.visibility = View.VISIBLE
        }

        notif_rety_btn.setOnClickListener {
            if (checkNetworkConnection(activity?.applicationContext!!)) {
                notify_no_net_layout.visibility = View.GONE
                notifications_recycler.visibility = View.VISIBLE
            }
        }
    }

}