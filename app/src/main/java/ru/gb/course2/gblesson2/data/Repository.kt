package ru.gb.course2.gblesson2.data

import ru.gb.course2.gblesson2.data.Weather

class Repository : IRepository {
    override fun getWeatherFromServer(): Weather {
        return Weather()
    }

    override fun getWeatherFromLocalStorage(): Weather {
        return Weather()
    }
}