package com.eugene.mockup.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Thread.sleep

class MainViewModel(private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()) : ViewModel() {

    fun getLiveData(): LiveData<AppState> {
        getDataFromLocac();
        return liveDataToObserve;
    }

    fun getDataFromLocac() {
        liveDataToObserve.value = AppState.Loading;

        Thread {
            sleep(2000);
            liveDataToObserve.postValue(AppState.Success("Ok"));
        }.start();
    }

}