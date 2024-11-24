package com.me.e_learningapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CourseDao {

    @Insert
    suspend fun insert(course: Course)

    @Query("SELECT * FROM my_table")
    suspend fun getAllCourse() : List<Course>

    @Insert
    suspend fun addCourse(course: Course)

    @Update
    suspend fun updateCourse(course: Course)
}