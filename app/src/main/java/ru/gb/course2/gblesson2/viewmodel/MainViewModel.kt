package ru.gb.course2.gblesson2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.gb.course2.gblesson2.data.AppState
import ru.gb.course2.gblesson2.data.IRepository
import ru.gb.course2.gblesson2.data.Repository
import java.lang.Thread.sleep

class MainViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: IRepository = Repository()
) :
    ViewModel() {
    fun getLiveData() = liveDataToObserve
    fun getWeatherFromLocalSourceRus() = getDataFromLocalSource(isRussian = true)
    fun getWeatherFromLocalSourceWorld() = getDataFromLocalSource(isRussian = false)
    fun getWeatherFromRemoteSource() = getDataFromLocalSource(isRussian = true)
    private fun getDataFromLocalSource(isRussian: Boolean) {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(3000)
            liveDataToObserve.postValue(
                AppState.Success(
                    if (isRussian) repository.getWeatherFromLocalStorageRus()
                    else repository.getWeatherFromLocalStorageWorld()
                )
            )
        }.start()
    }
}
