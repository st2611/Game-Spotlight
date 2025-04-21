package com.example.feature.quiz.presentation.ques.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.firebase.firestore.usecase.QuestionUseCase
import com.example.feature.quiz.presentation.ques.event.QuesUiEvent
import com.example.feature.quiz.presentation.ques.intent.QuesIntent
import com.example.feature.quiz.presentation.ques.state.QuesState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuesViewModel(
    private val questionUseCase: QuestionUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(QuesState())
    val state: StateFlow<QuesState> = _state.asStateFlow()

    private val _event = MutableSharedFlow<QuesUiEvent>()
    val event = _event.asSharedFlow()


    fun onIntent(intent: QuesIntent) {
        when (intent) {
            is QuesIntent.LoadQuestions -> loadQuestions()
            is QuesIntent.SelectAnswer -> {
                _state.update { current ->
                    current.copy(
                        selectedAnswers = current.selectedAnswers.toMutableMap()
                            .apply { put(intent.questionId, intent.selectedIndex) }
                    )
                }
            }

            is QuesIntent.SubmitAnswers -> submitAnswers()
        }
    }

    private fun loadQuestions() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                val questions = questionUseCase.getQuestionsUseCase()
                _state.value = _state.value.copy(questions = questions, isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value.copy(error = e.message, isLoading = false)
            }
        }
    }

    private fun submitAnswers() {
        viewModelScope.launch {
            val current = _state.value
            val score = current.questions.count {
                current.selectedAnswers[it.id] == it.correctIndex
            }
            _state.value = current.copy(score = score)

            _event.emit(QuesUiEvent.NavigateToResult(score, current.questions.size))

            _state.value = QuesState()
        }
    }
}