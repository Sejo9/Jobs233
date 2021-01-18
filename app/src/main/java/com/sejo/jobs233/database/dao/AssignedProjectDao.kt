package com.sejo.jobs233.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sejo.jobs233.database.entity.AssignedProjectsEntity

@Dao
interface AssignedProjectDao {

    @Insert
    fun insertProjects(projects: List<AssignedProjectsEntity>)

    @Delete
    fun deleteProject(project: AssignedProjectsEntity)

    @Query("SELECT * FROM assigned_projects_table WHERE id = :projectID")
    fun retrieveProject(projectID: Int): LiveData<AssignedProjectsEntity>

    @Query("SELECT * FROM assigned_projects_table")
    fun retrieveAllProject(): LiveData<List<AssignedProjectsEntity>>
}