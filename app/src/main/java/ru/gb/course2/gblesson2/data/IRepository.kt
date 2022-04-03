package ru.gb.course2.gblesson2.data

import ru.gb.course2.gblesson2.data.Weather

interface IRepository {
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocalStorage(): Weather
}