package com.example.apod_nasaapi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.apod_nasaapi.service.model.NasaModel
import com.example.apod_nasaapi.service.repository.RemoteDataSource

class NasaViewModel(application : Application) : AndroidViewModel(application) {

    private val mRemoteDataSource = RemoteDataSource()

    private val mNasaResponse = MutableLiveData<NasaModel>()
    val nasaResponse: LiveData<NasaModel> = mNasaResponse

    private val mErrorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = mErrorMessage

    fun getDataFromUserDate(date : String) {
        mRemoteDataSource.getDataFromUserDate(date, { nasaModel ->
            mNasaResponse.value = nasaModel
        }, { stringMessage ->
            mErrorMessage.postValue(stringMessage)
        })
    }
}
