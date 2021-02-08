package com.sejo.jobs233.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.sejo.jobs233.adapters.viewpager.BasePagerAdapter
import com.sejo.jobs233.databinding.FragmentProjectsBinding
import com.sejo.jobs233.fragments.projects.AssignedFragment
import com.sejo.jobs233.fragments.projects.OngoingFragment
import com.sejo.jobs233.models.TabFragment

class ProjectsFragment : Fragment() {

    private var _binding: FragmentProjectsBinding? = null
    private val binding get() = _binding!!

    private val tabFragments = listOf(
        TabFragment("Ongoing", OngoingFragment()),
        TabFragment("Assigned", AssignedFragment())
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProjectsBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.projectsViewpager.adapter = BasePagerAdapter(
            childFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
            tabFragments
        )

        binding.projectsTablayout.setupWithViewPager(binding.projectsViewpager)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}