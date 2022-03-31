package ru.gb.course2.gblesson2.ui.main

sealed class AppState {
    data class Success(val weather: Any) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
