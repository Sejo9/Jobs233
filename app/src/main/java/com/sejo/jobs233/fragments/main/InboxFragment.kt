package com.sejo.jobs233.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.sejo.jobs233.adapters.viewpager.BasePagerAdapter
import com.sejo.jobs233.databinding.FragmentInboxBinding
import com.sejo.jobs233.fragments.inbox.MessagesFragment
import com.sejo.jobs233.fragments.inbox.NotificationsFragment
import com.sejo.jobs233.models.TabFragment

class InboxFragment : Fragment() {

    private var _binding: FragmentInboxBinding? = null
    private val binding get() = _binding!!

    private val tabFragments = listOf(
        TabFragment("Messages", MessagesFragment()),
        TabFragment("Notifications", NotificationsFragment())
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInboxBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.inboxViewpager.adapter = BasePagerAdapter(
            childFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
            tabFragments
        )

        binding.inboxTablayout.setupWithViewPager(binding.inboxViewpager)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}