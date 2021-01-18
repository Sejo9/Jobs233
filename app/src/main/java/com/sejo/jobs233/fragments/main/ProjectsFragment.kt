package com.sejo.jobs233.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.sejo.jobs233.R
import com.sejo.jobs233.adapters.viewpager.BasePagerAdapter
import com.sejo.jobs233.fragments.projects.AssignedFragment
import com.sejo.jobs233.fragments.projects.OngoingFragment
import com.sejo.jobs233.models.data.TabFragment
import kotlinx.android.synthetic.main.fragment_projects.*

class ProjectsFragment : Fragment() {

    private val tabFragments = listOf(
        TabFragment("Ongoing", OngoingFragment()),
        TabFragment("Assigned", AssignedFragment())
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_projects, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        projects_viewpager.adapter = BasePagerAdapter(
            childFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
            tabFragments
        )

        projects_tablayout.setupWithViewPager(projects_viewpager)
    }
}