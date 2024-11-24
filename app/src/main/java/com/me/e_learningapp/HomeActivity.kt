package com.me.e_learningapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Room
import com.me.e_learningapp.database.Course
import com.me.e_learningapp.database.CourseDatabase
import com.me.e_learningapp.ui.theme.ELearningAppTheme
import com.me.e_learningapp.viewmodels.CourseViewModel
import com.me.e_learningapp.viewmodels.CourseViewModelFactory

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val courseDatabase by lazy {
                Room.databaseBuilder(applicationContext, CourseDatabase::class.java, "course_database")
                    .build()
            }

            val repository by lazy {
                CourseRepository(courseDatabase.courseDao())
            }

            val courseViewModel: CourseViewModel by viewModels {
                CourseViewModelFactory(repository)
            }

            ELearningAppTheme {
                HomeScreenMainContent(courseViewModel)
            }
        }
    }
}

@Composable
fun HomeScreenMainContent(viewModel: CourseViewModel) {
    val courses by viewModel.allCourseLD.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(courses) {
            CourseCard(course = it)
        }
    }
}


@Composable
fun CourseCard(course: Course) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = course.courseImage),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentScale = ContentScale.FillWidth
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 14.dp, end = 14.dp, top = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = course.interestedGeeks,
                    style = TextStyle(
                        fontFamily = fontMuslish,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                )
                Text(
                    text = course.rating,
                    style = TextStyle(
                        fontFamily = fontMuslish,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )
            }

            Text(
                text = course.courseName,
                style = TextStyle(
                    fontFamily = fontMuslish,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black,
                    fontSize = 18.sp
                ),
                modifier = Modifier.padding(start = 14.dp, top = 20.dp)
            )

            Text(
                text = course.courseDifficulty,
                style = TextStyle(
                    fontFamily = fontMuslish,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                ),
                modifier = Modifier.padding(start = 14.dp, top = 20.dp)
            )

            Text(
                text = course.seatsLeft,
                style = TextStyle(
                    fontFamily = fontMuslish,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xffff6666),
                ),
                modifier = Modifier.padding(start = 14.dp, top = 12.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xffc8e0ce))
                    .padding(top = 16.dp, bottom = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .background(color = Color(0xffc8e0ce))
                        .border(
                            BorderStroke(1.dp, Color(0xff00b300)),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(top = 8.dp, bottom = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Explore")
                }
            }
        }
    }
}











