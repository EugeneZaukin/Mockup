package com.eugene.mockup.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eugene.mockup.model.AllValutes
import com.eugene.mockup.model.Valute
import com.eugene.mockup.model.repo.IRepository
import com.eugene.mockup.model.repo.RepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val SERVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "Ошибка запроса на сервер"

class MainViewModel(private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
                    private val repository: IRepository = RepositoryImpl()) : ViewModel() {

    fun getLiveData(): LiveData<AppState> {
        return liveDataToObserve;
    }

    fun getValuteFromServer(): LiveData<AppState> {
        getDataFromServer();
        return liveDataToObserve;
    }

    private fun getDataFromServer() {
        liveDataToObserve.value = AppState.Loading;

        val callback = object : Callback<AllValutes> {
            override fun onResponse(call: Call<AllValutes>, response: Response<AllValutes>) {
                val serverBody = response.body()

                if (response.isSuccessful && serverBody != null) {
                    val listOfValutes = serverBody.listOfValutesf;
                    listOfValutes?.add(0,
                        Valute("643", "RUB", "1", "Рубль", "1"))
                    liveDataToObserve.postValue(AppState.Success(listOfValutes));
                } else {
                    liveDataToObserve.postValue(AppState.Error(Throwable(SERVER_ERROR)));
                }
            }

            override fun onFailure(call: Call<AllValutes>, t: Throwable) {
                liveDataToObserve.postValue(AppState.Error(Throwable(t.message + REQUEST_ERROR)));
            }
        }
        repository.getValutesFromServer(callback)
    }
}