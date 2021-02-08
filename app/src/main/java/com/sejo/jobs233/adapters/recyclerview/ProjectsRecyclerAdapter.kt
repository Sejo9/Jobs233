package com.sejo.jobs233.adapters.recyclerview

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sejo.custodianlibrary.CircularImageView
import com.sejo.jobs233.databinding.ProjectListItemBinding
import com.sejo.jobs233.fragments.main.ProjectsFragmentDirections
import com.sejo.jobs233.models.data.project.ProjectAdapterItem
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

class ProjectsRecyclerAdapter :
    ListAdapter<ProjectAdapterItem, ProjectsRecyclerAdapter.ProjectViewHolder>(ProjectDiffCallback()) {

    private var projectType: Int = 0

    companion object {
        const val ONGOING_PROJECT = 1
        const val ASSIGNED_PROJECT = 2
    }

    inner class ProjectViewHolder(binding: ProjectListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val projectTitle: TextView = binding.projectTitle
        private val projectBudget: TextView = binding.projectBudget
        private val projectDescription: TextView = binding.projectDescription
        private val projectTagsSkills: TextView = binding.projectTagsSkills
        private val projectClientName: TextView = binding.projectClientName
        val projectClientPic: CircularImageView = binding.projectClientPic

        fun bind(item: ProjectAdapterItem) {
            projectTitle.text = item.title
            projectBudget.text = item.budget
            projectDescription.text = item.description
            projectTagsSkills.text = item.tags.plus("," + item.skills)
            projectClientName.text = item.client.name

            Picasso.get().load(item.client.profile.picture).into(object : Target {
                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    projectClientPic.setCircularBitmap(bitmap!!)
                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                }

                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                }
            })

            when (projectType) {
                ONGOING_PROJECT -> {
                    itemView.setOnClickListener {
                        val pID = item.id
                        val action =
                            ProjectsFragmentDirections.actionProjectsFragmentToProjectBidActivity(
                                pID
                            )
                        Navigation.findNavController(it).navigate(action)

                    }
                }
                ASSIGNED_PROJECT -> {
                    itemView.setOnClickListener {
                        val pID = item.id
                        val action =
                            ProjectsFragmentDirections.actionProjectsFragmentToProjectViewActivity(
                                pID
                            )
                        Navigation.findNavController(it).navigate(action)
                    }
                }
                else -> {
                    Log.e(
                        "ProjectsRecyclerAdapter",
                        "Project type not set or incorrect type provided"
                    )
                }
            }
        }
    }

    class ProjectDiffCallback : DiffUtil.ItemCallback<ProjectAdapterItem>() {
        override fun areItemsTheSame(
            oldItem: ProjectAdapterItem,
            newItem: ProjectAdapterItem
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: ProjectAdapterItem,
            newItem: ProjectAdapterItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val binding =
            ProjectListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ProjectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)
    }

    fun setProjectType(projectType: Int) {
        if (projectType == ONGOING_PROJECT) {
            this.projectType = ONGOING_PROJECT
        } else if (projectType == ASSIGNED_PROJECT) {
            this.projectType = ASSIGNED_PROJECT
        }
    }
}