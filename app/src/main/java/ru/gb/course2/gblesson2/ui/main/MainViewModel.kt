package ru.gb.course2.gblesson2.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Thread.sleep

class MainViewModel(private val liveDataToObserve: MutableLiveData<Any> = MutableLiveData()) :
    ViewModel() {

    fun getLiveData(): MutableLiveData<Any> {
        getDataFromLocALsOURCE()
        return liveDataToObserve
    }

    private fun getDataFromLocALsOURCE() {
        Thread {
            sleep(2000)
            liveDataToObserve.postValue(Any())
        }.start()
    }
}