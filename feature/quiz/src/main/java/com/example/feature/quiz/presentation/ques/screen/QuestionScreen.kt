package com.example.feature.quiz.presentation.ques.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.feature.quiz.presentation.ques.event.QuesUiEvent
import com.example.feature.quiz.presentation.ques.intent.QuesIntent
import com.example.feature.quiz.presentation.ques.viewmodel.QuesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun QuestionScreen(
    navController: NavHostController,
    viewModel: QuesViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val eventFlow = viewModel.event

    LaunchedEffect(Unit) {
        viewModel.onIntent(QuesIntent.LoadQuestions)

        eventFlow.collect { event ->
            when (event) {
                is QuesUiEvent.NavigateToResult -> {
                    navController.navigate("result_screen/${event.score}/${event.total}")
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .navigationBarsPadding()
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else if (state.error != null) {
            Text(text = "Error: ${state.error}")
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(state.questions) { question ->
                    QuestionItem(
                        question = question,
                        selectedIndex = state.selectedAnswers[question.id],
                        onAnswerSelected = { selectedIndex ->
                            viewModel.onIntent(
                                QuesIntent.SelectAnswer(
                                    questionId = question.id,
                                    selectedIndex = selectedIndex
                                )
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            Button(
                onClick = { viewModel.onIntent(QuesIntent.SubmitAnswers) },

                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Submit")
            }
        }
    }
}

