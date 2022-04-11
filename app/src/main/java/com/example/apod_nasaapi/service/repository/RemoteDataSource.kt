package com.example.apod_nasaapi.service.repository

import com.example.apod_nasaapi.service.model.NasaModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    private val retrofitClient = RetrofitClient.createService(NasaService::class.java)

    fun getDataFromUserDate(
        userDate: String,
        onSuccess: (NasaModel) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val call: Call<NasaModel> = retrofitClient.getDataFromUserDate(userDate)
        call.enqueue(object : Callback<NasaModel> {
            override fun onResponse(call: Call<NasaModel>, response: Response<NasaModel>) {
                if (response.isSuccessful) {
                    response.body()?.let { nasaModel ->
                        onSuccess.invoke(nasaModel)

                    }
                } else {
                    onFailure.invoke(response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<NasaModel>, throwable: Throwable) {
                throwable.message?.let { stringMessage ->
                    onFailure.invoke(stringMessage)
                }
            }

        })
    }
}
