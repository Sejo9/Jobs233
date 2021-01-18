package com.sejo.jobs233.fragments.projects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sejo.jobs233.R
import com.sejo.jobs233.adapters.recyclerview.ProjectsRecyclerAdapter
import com.sejo.jobs233.adapters.recyclerview.ProjectsRecyclerAdapter.Companion.ASSIGNED_PROJECT
import com.sejo.jobs233.viewmodels.projects.AssignedProjectsViewModel
import kotlinx.android.synthetic.main.fragment_assigned.*


class AssignedFragment : Fragment() {

    private lateinit var adapter: ProjectsRecyclerAdapter
    private lateinit var viewModel: AssignedProjectsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        adapter = ProjectsRecyclerAdapter(activity?.applicationContext!!)

        viewModel = ViewModelProvider(this).get(AssignedProjectsViewModel::class.java)

        adapter.setProjectType(ASSIGNED_PROJECT)

        viewModel.projectsList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_assigned, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        assigned_progress.visibility = View.VISIBLE

        viewModel.projectsLoaded.observe(viewLifecycleOwner, Observer {
            if (it) {
                assigned_progress.visibility = View.GONE
                assigned_recycler.adapter = adapter
                viewModel.projectsLoadingComplete()
            }
        })


    }


}