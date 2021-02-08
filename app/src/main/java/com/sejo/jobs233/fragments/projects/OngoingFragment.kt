package com.sejo.jobs233.fragments.projects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sejo.jobs233.adapters.recyclerview.ProjectsRecyclerAdapter
import com.sejo.jobs233.adapters.recyclerview.ProjectsRecyclerAdapter.Companion.ONGOING_PROJECT
import com.sejo.jobs233.databinding.FragmentOngoingBinding
import com.sejo.jobs233.viewmodels.projects.OngoingProjectsViewModel


class OngoingFragment : Fragment() {

    private var _binding: FragmentOngoingBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: OngoingProjectsViewModel
    private lateinit var adapter: ProjectsRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentOngoingBinding.inflate(layoutInflater, container, false)

        adapter = ProjectsRecyclerAdapter()

        viewModel = ViewModelProvider(this).get(OngoingProjectsViewModel::class.java)

        adapter.setProjectType(ONGOING_PROJECT)

        viewModel.projectsList.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ongoingProgress.visibility = View.VISIBLE

        viewModel.projectsLoaded.observe(viewLifecycleOwner, {
            if (it) {
                binding.ongoingProgress.visibility = View.GONE
                binding.ongoingRecycler.adapter = adapter
                viewModel.projectsLoadingComplete()
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}