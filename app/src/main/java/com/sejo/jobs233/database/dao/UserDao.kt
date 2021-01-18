package com.sejo.jobs233.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sejo.jobs233.database.entity.UserEntity

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: UserEntity)

    @Update
    fun updateUser(user: UserEntity)

    @Query("SELECT * FROM users_table WHERE id = :userID")
    fun retrieveUser(userID: Int): LiveData<UserEntity>

    @Delete
    fun deleteUser(user: UserEntity)
}