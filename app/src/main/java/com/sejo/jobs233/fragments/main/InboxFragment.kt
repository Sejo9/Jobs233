package com.sejo.jobs233.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.sejo.jobs233.R
import com.sejo.jobs233.adapters.viewpager.BasePagerAdapter
import com.sejo.jobs233.fragments.inbox.MessagesFragment
import com.sejo.jobs233.fragments.inbox.NotificationsFragment
import com.sejo.jobs233.models.data.TabFragment
import kotlinx.android.synthetic.main.fragment_inbox.*

class InboxFragment : Fragment() {

    private val tabFragments = listOf(
        TabFragment("Messages", MessagesFragment()),
        TabFragment("Notifications", NotificationsFragment())
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inbox, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inbox_viewpager.adapter = BasePagerAdapter(
            childFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
            tabFragments
        )

        inbox_tablayout.setupWithViewPager(inbox_viewpager)
    }

}