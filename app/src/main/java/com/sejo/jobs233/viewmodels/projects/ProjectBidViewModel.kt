package com.sejo.jobs233.viewmodels.projects

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sejo.jobs233.extra.parseOffsetDateTime
import com.sejo.jobs233.models.data.project.Project
import com.sejo.jobs233.network.API
import com.sejo.jobs233.network.BASE_SITE_URL
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProjectBidViewModel(projectID: Int) : ViewModel() {

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

    private val _bitmap = MutableLiveData<Bitmap>()
    val bitmap: LiveData<Bitmap>
        get() = _bitmap

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
                    loadPicViaPicasso()
                }
            } else {
                Log.e("ProjectBidViewModel", "Success: " + response.success.toString())
            }
        } catch (e: Exception) {
            Log.e("ProjectBidViewModel2", e.toString())
        }

    }

    private suspend fun loadPicViaPicasso() {
        withContext(Dispatchers.Main) {
            Picasso.get().load(BASE_SITE_URL + project.value!!.user.profile.picture)
                .into(object : Target {
                    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                        _bitmap.value = bitmap
                    }

                    override fun onBitmapFailed(e: java.lang.Exception?, errorDrawable: Drawable?) {
                    }

                    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                    }
                })
        }
    }
}