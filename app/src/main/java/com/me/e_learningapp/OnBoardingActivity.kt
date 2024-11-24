package com.me.e_learningapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.rememberPagerState
import com.google.firebase.auth.FirebaseAuth
import com.me.e_learningapp.ui.theme.ELearningAppTheme

class OnBoardingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val auth = FirebaseAuth.getInstance()
        setContent {
            ELearningAppTheme {
                if(auth.currentUser == null) {
                    OnboardingScreen()
                } else {
                    startActivity(
                        Intent(this, HomeActivity::class.java)
                    )
                    finish()
                }
            }
        }
    }
}

@Composable
@Preview
fun OnboardingScreen() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 24.dp, top = 24.dp)
                .clickable {
                    context.startActivity(
                        Intent(context, LogInActivity::class.java)
                    )
                    (context as Activity).finish()
                },
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(
                text = "skip",
                style = TextStyle(fontSize = 18.sp, color = Color.Blue, fontFamily = fontMuslish),
                fontWeight = FontWeight.ExtraBold
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
        ScrollableIntroUI()

        Button(onClick = {
            context.startActivity(
                Intent(context, SignUpActivity::class.java)
            )
            (context as Activity).finish()
        }, modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(top = 20.dp)) {
            Text(text = "Register")
        }

        Button(onClick = {
            context.startActivity(
                Intent(context, LogInActivity::class.java)
            )
            (context as Activity).finish()
        }, modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(top = 16.dp)) {
            Text(text = "Log In")
        }
    }
}

@Composable
fun ScrollableIntroUI() {
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        com.google.accompanist.pager.HorizontalPager(count = 3, state = pagerState) { currentPage ->
            when (currentPage) {
                0 -> IntroScreenUI(
                    image = R.drawable.onboardin_1,
                    mainText = "Better way to learning is calling you!",
                    desText = "There's alot in the app you can learn just hang in tight and have patience"
                )
                1 -> IntroScreenUI(
                    image = R.drawable.onboarding_2,
                    mainText = "Find yourself by doing whatever you do!!",
                    desText = "There's alot in the app you can learn just hang in tight and have patience"
                )
                2 -> IntroScreenUI(
                    image = R.drawable.onboarding_3,
                    mainText = "It's not just learning, its a promise",
                    desText = "There's alot in the app you can learn just hang in tight and have patience"
                )
            }
        }

        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(3) { index ->
                Box(
                    modifier = Modifier
                        .size(if (pagerState.currentPage == index) 12.dp else 8.dp)
                        .background(
                            color = if (pagerState.currentPage == index) Color.Black else Color.Gray,
                            shape = CircleShape
                        )
                ) {

                }
            }
        }
    }
}

@Composable
fun IntroScreenUI(image: Int, mainText: String, desText: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.White)
            .padding(start = 24.dp, end = 24.dp)
    ) {

        Image(painter = painterResource(id = image), contentDescription = null)

        Text(
            text = mainText,
            style = TextStyle(
                fontSize = 24.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontFamily = fontMuslish,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(top = 30.dp)
        )

        Text(
            text = desText,
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                fontFamily = fontMuslish,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(top = 24.dp)
        )
    }
}

val fontMuslish = FontFamily(
    Font(R.font.muslish, FontWeight.Normal)
)









