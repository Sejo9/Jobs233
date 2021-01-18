package com.sejo.jobs233.fragments.inbox

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sejo.jobs233.R
import com.sejo.jobs233.activities.SearchUserActivity
import com.sejo.jobs233.adapters.recyclerview.MessageContactsRecyclerAdapter
import com.sejo.jobs233.models.data.inbox.MessageContactAdapterItem
import kotlinx.android.synthetic.main.fragment_messages.*

class MessagesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_messages, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MessageContactsRecyclerAdapter()

        val adapterItems = ArrayList<MessageContactAdapterItem>()



        adapter.submitList(adapterItems)



        new_message_fab.setOnClickListener {
            startActivity(Intent(activity, SearchUserActivity::class.java))
        }
    }
}