package com.example.trecker_lr2.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import com.example.trecker_lr2.model.Habit
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*

class HabitViewModel : ViewModel() {

    private val initialHabits = listOf(
        Habit("1", "Пити воду", false),
        Habit("2", "Ранкова зарядка", false),
        Habit("3", "Читати 20 хв", false),
        Habit("4", "Прогулянка", false),
        Habit("5", "Вчити щось нове", false),
        Habit("6", "Лягти спати до 23:00", false)
    )

    private val _habits = MutableStateFlow(initialHabits)
    val habits: StateFlow<List<Habit>> = _habits.asStateFlow()

    val completedCount: StateFlow<Int> =
        _habits
            .map { list -> list.count { it.isDone } }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = 0
            )

    fun toggleHabit(id: String) {
        _habits.value = _habits.value.map {
            if (it.id == id) it.copy(isDone = !it.isDone)
            else it
        }
    }


    fun resetHabits() {
        _habits.value = _habits.value.map {
            it.copy(isDone = false)
        }
    }
}