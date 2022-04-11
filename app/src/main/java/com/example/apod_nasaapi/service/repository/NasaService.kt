package com.example.apod_nasaapi.service.repository

import com.example.apod_nasaapi.service.model.NasaModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaService {

    @GET("apod")
        fun getDataFromUserDate(@Query("date", encoded = true) query: String): Call<NasaModel>
}
