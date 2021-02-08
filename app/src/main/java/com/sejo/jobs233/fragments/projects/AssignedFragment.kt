package com.sejo.jobs233.fragments.projects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sejo.jobs233.adapters.recyclerview.ProjectsRecyclerAdapter
import com.sejo.jobs233.adapters.recyclerview.ProjectsRecyclerAdapter.Companion.ASSIGNED_PROJECT
import com.sejo.jobs233.databinding.FragmentAssignedBinding
import com.sejo.jobs233.viewmodels.projects.AssignedProjectsViewModel


class AssignedFragment : Fragment() {

    private var _binding: FragmentAssignedBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ProjectsRecyclerAdapter
    private lateinit var viewModel: AssignedProjectsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAssignedBinding.inflate(layoutInflater, container, false)

        adapter = ProjectsRecyclerAdapter()

        viewModel = ViewModelProvider(this).get(AssignedProjectsViewModel::class.java)

        adapter.setProjectType(ASSIGNED_PROJECT)

        viewModel.projectsList.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.assignedProgress.visibility = View.VISIBLE

        viewModel.projectsLoaded.observe(viewLifecycleOwner, {
            if (it) {
                binding.assignedProgress.visibility = View.GONE
                binding.assignedRecycler.adapter = adapter
                viewModel.projectsLoadingComplete()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}