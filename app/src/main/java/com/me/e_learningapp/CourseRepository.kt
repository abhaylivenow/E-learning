package com.me.e_learningapp

import com.me.e_learningapp.database.Course
import com.me.e_learningapp.database.CourseDao

class CourseRepository(
    private val courseDao: CourseDao
) {

    suspend fun getAllCourse(): List<Course> = courseDao.getAllCourse()

    suspend fun insert(course: Course) = courseDao.insert(course)
}