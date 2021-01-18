package com.sejo.jobs233.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sejo.jobs233.database.dao.AssignedProjectDao
import com.sejo.jobs233.database.dao.ProfileDao
import com.sejo.jobs233.database.dao.UserDao
import com.sejo.jobs233.database.entity.AssignedProjectsEntity
import com.sejo.jobs233.database.entity.ProfileEntity
import com.sejo.jobs233.database.entity.UserEntity

@Database(
    entities = [UserEntity::class, AssignedProjectsEntity::class, ProfileEntity::class],
    version = 1,
    exportSchema = false
)
abstract class JobsDatabase : RoomDatabase() {

    abstract val userDao: UserDao
    abstract val assignedProjectDao: AssignedProjectDao
    abstract val profileDao: ProfileDao

    companion object {

        @Volatile
        private var INSTANCE: JobsDatabase? = null

        fun getInstance(context: Context): JobsDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        JobsDatabase::class.java,
                        "jobs233_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}