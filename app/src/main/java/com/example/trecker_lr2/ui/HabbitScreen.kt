package com.example.trecker_lr2.ui
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.trecker_lr2.viewmodel.HabitViewModel

@Composable
fun HabitScreen(viewModel: HabitViewModel) {

    val habits by viewModel.habits.collectAsState()
    val completed by viewModel.completedCount.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Виконано сьогодні: $completed з ${habits.size}",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.resetHabits() }) {
            Text("Скинути день")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(habits, key = { it.id }) { habit ->
                HabitCard(
                    habit = habit,
                    onToggle = { viewModel.toggleHabit(it) }
                )
            }
        }
    }
}
