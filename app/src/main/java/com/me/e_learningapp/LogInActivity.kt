package com.me.e_learningapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth

class LogInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val auth = FirebaseAuth.getInstance()
        setContent {
            LoginScreen(auth)
//            if(auth.currentUser == null) {
//                LoginScreen(auth)
//            } else {
//                startActivity(
//                    Intent(this, MainActivity::class.java)
//                )
//                finish()
//            }
        }
    }
}

@Composable
fun GradientBackground() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(
            brush = Brush.linearGradient(
                colors = listOf(
                    Color(0xFFffcc99),
                    Color(0xFFffb3b3),
                    Color(0xFFff99dd)
                )
            )
        ))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StyledTextField(
    value: String,
    onValueChange:(String) -> Unit,
    labelText: String = "Enter text..."
) {

    TextField(
        value = value, onValueChange = {
            onValueChange(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, start = 12.dp, end = 12.dp)
            .shadow(
                elevation = 12.dp,
                shape = RoundedCornerShape(30.dp)
            ),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.surface,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.primary
        ),
        placeholder = {
            Text(text = labelText)
        },
    )
}

@Composable
fun LoginScreen(auth: FirebaseAuth) {
    GradientBackground()
    var isLoading by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    if(isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            //CircularProgressIndicator()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        StyledTextField(value = email, onValueChange = {
            email = it
        }, "Email")

        StyledTextField(value = password, onValueChange = {
            password = it
        }, "Password")

        Spacer(modifier = Modifier.height(30.dp))

        Button(onClick = {
            isLoading = true
            Log.i("EMail",email)
            Log.i("Password",password)
            if(email.isNotBlank() && password.isNotBlank()) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if(task.isSuccessful) {
                            isLoading = false
                            Toast.makeText(context, "User Logged In Successfully", Toast.LENGTH_SHORT).show()
                            context.startActivity(
                                Intent(
                                    context, HomeActivity::class.java
                                )
                            )
                            (context as Activity).finish()
                        } else {
                            isLoading = false
                            Toast.makeText(context, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                isLoading = false
                Toast.makeText(context, "Please enter valid user name and password", Toast.LENGTH_SHORT).show()
            }
        }, modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth(0.5f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFff66ff)
            )
        ) {
            Text(text = "LOG IN")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Don't have an account? Create", modifier = Modifier
            .clickable {
                val intent = Intent(context, SignUpActivity::class.java)
                context.startActivity(
                    intent
                )
            })
    }
}