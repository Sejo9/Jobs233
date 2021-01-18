package com.sejo.jobs233.viewmodels.projects

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sejo.jobs233.models.data.project.ProjectAdapterItem
import com.sejo.jobs233.network.API
import kotlinx.coroutines.launch

class OngoingProjectsViewModel : ViewModel() {

    private val _projectsList = MutableLiveData<ArrayList<ProjectAdapterItem>>()
    val projectsList: LiveData<ArrayList<ProjectAdapterItem>>
        get() = _projectsList

    private val _projectsLoaded = MutableLiveData<Boolean>()
    val projectsLoaded: LiveData<Boolean>
        get() = _projectsLoaded

    init {
        _projectsList.value = ArrayList()
        _projectsLoaded.value = false
        getAllProjects()
    }

    private fun getAllProjects() = viewModelScope.launch {
        try {
            val response = API.instance.getAllProjects()

            if (response.success) {
                val projects = response.projects.data

                projects.forEach { project ->

                    val item = ProjectAdapterItem(
                        project.id,
                        project.title,
                        project.budget,
                        project.description,
                        project.tags,
                        project.skills,
                        project.user
                    )

                    _projectsList.value?.add(item)

                    _projectsLoaded.value = true
                }

            } else {
                Log.e("OngoingFragment1", "Success: " + response.success.toString())
            }
        } catch (e: Exception) {
            Log.e("OngoingFragment2", e.toString())
        }
    }

    fun projectsLoadingComplete() {
        _projectsLoaded.value = false
    }
}