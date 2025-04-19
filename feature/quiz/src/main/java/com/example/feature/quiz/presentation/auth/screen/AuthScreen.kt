package com.example.feature.quiz.presentation.auth.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.feature.quiz.navigation.QuizScreen
import com.example.feature.quiz.presentation.auth.intent.AuthIntent
import com.example.feature.quiz.presentation.auth.viewmodel.AuthViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthScreen(
    navController: NavHostController,
    viewModel: AuthViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    val emailFocusRequester = remember { FocusRequester() }
    val isFormValid = state.email.isNotBlank() && state.password.isNotBlank()


    LaunchedEffect(state.isSignInSuccess) {
        if (state.isSignInSuccess) {
            navController.navigate(QuizScreen.Question.route) {
                popUpTo(QuizScreen.Auth.route) { inclusive = true }
            }
            Toast.makeText(context, "Sign In Success", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(state.isSignUpSuccess) {
        if (state.isSignUpSuccess) {
            emailFocusRequester.requestFocus()
            Toast.makeText(context, "Sign Up Success", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(state.error) {
        state.error?.let {
            emailFocusRequester.requestFocus()
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = state.email,
                onValueChange = {
                    viewModel.onIntent(AuthIntent.EmailChanged(it))
                },
                label = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(emailFocusRequester)
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = state.password,
                onValueChange = {
                    viewModel.onIntent(AuthIntent.PasswordChanged(it))
                },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (!isFormValid) {
                        Toast.makeText(
                            context,
                            "Please enter full email and password",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@Button
                    }
                    viewModel.onIntent(AuthIntent.SignInClicked)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Sign In")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (!isFormValid) {
                        Toast.makeText(
                            context,
                            "Please enter full email and password",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@Button
                    }
                    viewModel.onIntent(AuthIntent.SignUpClicked)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Sign Up")
            }
            Spacer(modifier = Modifier.height(16.dp))
            if (state.isLoading) CircularProgressIndicator()
        }
    }
}
