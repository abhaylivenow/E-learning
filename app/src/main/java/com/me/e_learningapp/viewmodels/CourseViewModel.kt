package com.me.e_learningapp.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.me.e_learningapp.CourseRepository
import com.me.e_learningapp.R
import com.me.e_learningapp.database.Course
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CourseViewModel(
    private val courseRepository: CourseRepository
) : ViewModel() {

    val allCourseLD = MutableStateFlow<List<Course>>(emptyList())

    init {
        addSampleCourseIfNeeded()
        fetchCourses()
    }

    private fun addSampleCourseIfNeeded() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("CourseViewModelLogs","26")
            val existingContact = courseRepository.getAllCourse()
            Log.i("CourseViewModelLogs","28")
            if(existingContact.isEmpty()) {
                Log.i("CourseViewModelLogs","30")
                generateAllCourse().forEach {
                    courseRepository.insert(it)
                }
            }
        }
    }

    private fun fetchCourses() {
        viewModelScope.launch(Dispatchers.IO) {
            allCourseLD.value = courseRepository.getAllCourse()
        }
    }

    private fun generateAllCourse(): List<Course> {
        return listOf(
            Course(
                courseName = "Data Science Classroom Program",
                interestedGeeks = "7k+ interested Geeks",
                rating = "4.5/5",
                des = "Learn Data science from scratch in classroom",
                courseDifficulty = "Beginner to Advance",
                courseImage = R.drawable.course_1,
                seatsLeft = "4 seats left"
            ),
            Course(
                courseName = "Complete Data Analytics Program",
                interestedGeeks = "4k+ interested Geeks",
                rating = "4.0/5",
                des = "Learn Data science from scratch in classroom",
                courseDifficulty = "Beginner to Advance",
                courseImage = R.drawable.course_2,
                seatsLeft = "12 seats left"
            ),
            Course(
                courseName = "DSA For Interview Preparation",
                interestedGeeks = "50k+ interested Geeks",
                rating = "4.9/5",
                des = "Learn Data science from scratch in classroom",
                courseDifficulty = "Beginner to Advance",
                courseImage = R.drawable.course_3,
                seatsLeft = "3 seats left"
            ),
            Course(
                courseName = "DSA To Development: A Complete Guide",
                interestedGeeks = "424k+ interested Geeks",
                rating = "4.8/5",
                des = "Learn Data science from scratch in classroom",
                courseDifficulty = "Beginner to Advance",
                courseImage = R.drawable.course_4,
                seatsLeft = "15 seats left"
            ),
            Course(
                courseName = "Java Backend Development : Live",
                interestedGeeks = "100k+ interested Geeks",
                rating = "4.7/5",
                des = "Learn Data science from scratch in classroom",
                courseDifficulty = "Beginner to Advance",
                courseImage = R.drawable.course_5,
                seatsLeft = "50 seats left"
            ),
            Course(
                courseName = "Complete Interview Preparation",
                interestedGeeks = "10k+ interested Geeks",
                rating = "4.3/5",
                des = "Learn Data science from scratch in classroom",
                courseDifficulty = "Beginner to Advance",
                courseImage = R.drawable.course_6,
                seatsLeft = "40 seats left"
            ),
            Course(
                courseName = "Mastering Generative AI and ChatGPT",
                interestedGeeks = "70k+ interested Geeks",
                rating = "4.9/5",
                des = "Learn Data science from scratch in classroom",
                courseDifficulty = "Beginner to Advance",
                courseImage = R.drawable.course_7,
                seatsLeft = "4 seats left"
            ),
            Course(
                courseName = "Gate Data Science AI 2025",
                interestedGeeks = "12k+ interested Geeks",
                rating = "4.9/5",
                des = "Learn Data science from scratch in classroom",
                courseDifficulty = "Beginner to Advance",
                courseImage = R.drawable.course_8,
                seatsLeft = "40 seats left"
            ),
            Course(
                courseName = "Complete Software Testing Course",
                interestedGeeks = "71k+ interested Geeks",
                rating = "4.1/5",
                des = "Learn Data science from scratch in classroom",
                courseDifficulty = "Beginner to Advance",
                courseImage = R.drawable.course_9,
                seatsLeft = "4 seats left"
            ),
            Course(
                courseName = "Full Stack Development With React",
                interestedGeeks = "100k+ interested Geeks",
                rating = "4.8/5",
                des = "Learn Data science from scratch in classroom",
                courseDifficulty = "Beginner to Advance",
                courseImage = R.drawable.course_10,
                seatsLeft = "4 seats left"
            )
        )
    }
}






