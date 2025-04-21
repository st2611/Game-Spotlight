package com.example.feature.quiz.presentation.ques.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ResultScreen(
    score: Int,
    total: Int,
    onRetry: () -> Unit,
    onSignOut: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Result",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Correct Answer: $score / $total questions",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFF4CAF50)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button (
            onClick = onRetry
            ,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Try again")
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton (
            onClick = onSignOut,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sign Out")
        }
    }
}
