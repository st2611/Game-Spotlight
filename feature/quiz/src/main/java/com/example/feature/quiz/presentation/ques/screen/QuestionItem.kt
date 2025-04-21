package com.example.feature.quiz.presentation.ques.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.core.domain.firebase.firestore.model.Question

@Composable
fun QuestionItem(
    question: Question,
    selectedIndex: Int?,
    onAnswerSelected: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .background(Color(0xFFF6F6F6), shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Text(text = question.question, style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(12.dp))

        question.options.forEachIndexed { index, option ->
            val isSelected = selectedIndex == index
            val bgColor = if (isSelected) Color(0xFFCCE5FF) else Color.White

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .background(bgColor, shape = RoundedCornerShape(8.dp))
                    .clickable { onAnswerSelected(index) }
                    .padding(12.dp)
            ) {
                Text(text = option)
            }
        }
    }
}
