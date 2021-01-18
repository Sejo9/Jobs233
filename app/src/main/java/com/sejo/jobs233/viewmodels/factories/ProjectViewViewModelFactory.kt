package com.sejo.jobs233.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sejo.jobs233.viewmodels.projects.ProjectViewViewModel

class ProjectViewViewModelFactory(private val projectID: Int) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProjectViewViewModel::class.java)) {
            return ProjectViewViewModel(projectID) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}