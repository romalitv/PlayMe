package com.example.playme

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation

val DarkBlue = Color(0xFF0A192F)

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun RegisterPage() {
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "PlayMe",
                modifier = Modifier.padding(16.dp,16.dp,16.dp,100.dp),
                fontSize = 35.sp,
                fontFamily = customFontFamily,
                color = Color.Yellow
            )

            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Електронна адреса") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White
                ),
                textStyle = MaterialTheme.typography.bodySmall.copy(
                    fontFamily = customFontFamily,
                    fontSize = 16.sp,
                    color = Color.DarkGray
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Пароль") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White
                ),
                textStyle = MaterialTheme.typography.bodySmall.copy(
                    fontFamily = customFontFamily,
                    fontSize = 16.sp,
                    color = Color.DarkGray
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Підтвердіть пароль") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White
                ),
                textStyle = MaterialTheme.typography.bodySmall.copy(
                    fontFamily = customFontFamily,
                    fontSize = 16.sp,
                    color = Color.DarkGray
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { registerWithEmail(context, auth, email, password, confirmPassword) },
                modifier = Modifier.align(Alignment.End),
            ) {
                Text(
                    text = "Зареєструватися",
                    color = DarkBlue,
                    fontFamily = customFontFamily,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

fun registerWithEmail(
    context: Context,
    auth: FirebaseAuth,
    email: String,
    password: String,
    confirmPassword: String
) {
    if (password == confirmPassword) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Реєстрація успішна!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Помилка реєстрації: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    } else {
        Toast.makeText(context, "Паролі не співпадають", Toast.LENGTH_SHORT).show()
    }
}