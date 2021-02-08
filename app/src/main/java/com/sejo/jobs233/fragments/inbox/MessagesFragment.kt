package com.sejo.jobs233.fragments.inbox

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sejo.jobs233.adapters.recyclerview.MessageContactsRecyclerAdapter
import com.sejo.jobs233.databinding.FragmentMessagesBinding
import com.sejo.jobs233.models.data.inbox.MessageContactAdapterItem

class MessagesFragment : Fragment() {

    private var _binding: FragmentMessagesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessagesBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MessageContactsRecyclerAdapter()

        val adapterItems = ArrayList<MessageContactAdapterItem>()



        adapter.submitList(adapterItems)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}