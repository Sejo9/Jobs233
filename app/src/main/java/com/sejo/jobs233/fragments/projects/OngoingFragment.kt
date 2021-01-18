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
import com.sejo.jobs233.adapters.recyclerview.ProjectsRecyclerAdapter.Companion.ONGOING_PROJECT
import com.sejo.jobs233.viewmodels.projects.OngoingProjectsViewModel
import kotlinx.android.synthetic.main.fragment_ongoing.*


class OngoingFragment : Fragment() {

    private lateinit var viewModel: OngoingProjectsViewModel
    private lateinit var adapter: ProjectsRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        adapter = ProjectsRecyclerAdapter(activity?.applicationContext!!)

        viewModel = ViewModelProvider(this).get(OngoingProjectsViewModel::class.java)

        adapter.setProjectType(ONGOING_PROJECT)

        viewModel.projectsList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ongoing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ongoing_progress.visibility = View.VISIBLE

        viewModel.projectsLoaded.observe(viewLifecycleOwner, Observer {
            if (it) {
                ongoing_progress.visibility = View.GONE
                ongoing_recycler.adapter = adapter
                viewModel.projectsLoadingComplete()
            }
        })

    }

}