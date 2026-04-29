package com.example.trecker_lr2.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.trecker_lr2.model.Habit
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.CardDefaults

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration


@Composable
fun HabitCard(
    habit: Habit,
    onToggle: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onToggle(habit.id) },
        colors = CardDefaults.cardColors(
            containerColor = if (habit.isDone) Color(0xFFD0F0C0) else Color.White
        )
    ) {
        Text(
            text = habit.title,
            modifier = Modifier.padding(16.dp),
            textDecoration = if (habit.isDone)
                TextDecoration.LineThrough
            else TextDecoration.None
        )
    }
}