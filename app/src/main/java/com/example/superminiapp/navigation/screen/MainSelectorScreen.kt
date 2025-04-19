package com.example.superminiapp.navigation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.superminiapp.navigation.destination.MainScreenDestination

@Composable
fun MainSelectorScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { navController.navigate(MainScreenDestination.GameHub.route) }) {
            Text("Go to GameHub")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate(MainScreenDestination.Quiz.route) }) {
            Text("Go to Quiz")
        }
    }
}
