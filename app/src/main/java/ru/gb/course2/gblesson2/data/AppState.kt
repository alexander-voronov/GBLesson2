package ru.gb.course2.gblesson2.data

import ru.gb.course2.gblesson2.data.Weather

sealed class AppState {
    data class Success(val weatherData: List<Weather>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
