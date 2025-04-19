package com.example.superminiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.superminiapp.navigation.screen.MainAppNavigation
import com.example.superminiapp.ui.theme.SuperMiniAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SuperMiniAppTheme {
                MainAppNavigation()
            }
        }
    }
}
