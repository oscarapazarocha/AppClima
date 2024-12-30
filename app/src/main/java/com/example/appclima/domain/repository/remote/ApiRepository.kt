package com.example.appclima.domain.repository.remote

import com.example.appclima.domain.model.LugarResponse
import com.example.appclima.service.ApiService
import javax.inject.Inject


class ApiRepository @Inject constructor(
    private val apiService : ApiService
) {
    //private val apiService = ApiService()
    suspend fun getDatos(lat:String,lon:String):LugarResponse = apiService.getTemperatura(lat,lon)
}