package com.sejo.jobs233.viewmodels.projects

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sejo.jobs233.extra.parseOffsetDateTime
import com.sejo.jobs233.models.data.project.Project
import com.sejo.jobs233.network.API
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProjectViewViewModel(projectID: Int) : ViewModel() {

    private val id: Int = projectID

    private val _project = MutableLiveData<Project>()
    val project: LiveData<Project>
        get() = _project

    private val _created = MutableLiveData<String>()
    val created: LiveData<String>
        get() = _created

    private val _joined = MutableLiveData<String>()
    val joined: LiveData<String>
        get() = _joined

    private val _deadline = MutableLiveData<String>()
    val deadline: LiveData<String>
        get() = _deadline

    init {
        getProjectDetails()
    }

    private fun getProjectDetails() = viewModelScope.launch {

        try {
            val response = API.instance.getProject(id)

            if (response.success) {
                withContext(Dispatchers.Main) {
                    _project.value = response.project
                    _created.value = parseOffsetDateTime(project.value!!.created_at) as String?
                    _joined.value = parseOffsetDateTime(project.value!!.user.created_at) as String?
                    _deadline.value = parseOffsetDateTime(project.value!!.deadline) as String?
                }
            } else {
                Log.e("ProjectViewViewModel", "Success: " + response.success.toString())
            }
        } catch (e: Exception) {
            Log.e("ProjectViewViewModel2", e.toString())
        }

    }

}