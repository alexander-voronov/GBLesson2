package ru.gb.course2.gblesson2

interface IRepository {
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocalStorage(): Weather
}