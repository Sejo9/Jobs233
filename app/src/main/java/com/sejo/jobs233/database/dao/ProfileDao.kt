package com.sejo.jobs233.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sejo.jobs233.database.entity.ProfileEntity

@Dao
interface ProfileDao {

    @Insert
    fun insertProfile(profileEntity: ProfileEntity)

    @Update
    fun updateProfile(profileEntity: ProfileEntity)

    @Query("SELECT * FROM profile_table WHERE id = :profile_id")
    fun retrieveProfile(profile_id: Int): LiveData<ProfileEntity>

    @Delete
    fun deleteProfile(profileEntity: ProfileEntity)
}