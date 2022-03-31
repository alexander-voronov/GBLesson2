package ru.gb.course2.gblesson2.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Thread.sleep

class MainViewModel(private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()) :
    ViewModel() {

    fun getLiveData(): MutableLiveData<AppState> {
        return liveDataToObserve
    }

    private fun getDataFromLocalSource() {
        Thread {
            sleep(2000)
            liveDataToObserve.postValue(AppState.Success(Any()))
        }.start()
    }

    fun load() {
        liveDataToObserve.postValue(AppState.Loading)
        getDataFromLocalSource()
    }
}