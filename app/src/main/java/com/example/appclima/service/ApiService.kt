package com.example.appclima.service

import com.example.appclima.core.RetrofitConector
import com.example.appclima.domain.model.Current
import com.example.appclima.domain.model.LugarResponse
import com.example.appclima.domain.model.Unidades
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiService @Inject constructor() {
    private val retrofit = RetrofitConector.getRetrofit()

    suspend fun getTemperatura(lat:String,lon:String):LugarResponse{
        val argumento = "forecast?latitude=${lat}&longitude=${lon}&current=temperature_2m"
        return withContext(Dispatchers.IO){
            val response = retrofit.create(IApiService::class.java).getDatosApi(argumento)
            response.body()?: LugarResponse(
                latitud = 0F,
                longitud = 0F,
                tiempo = 0F,
                utctiempo = 0,
                timezone = "GMT",
                timezoneabb = "GMT",
                elevation = 0F,
                Unidades(tiempo = "iso8601", intervalo = "seconds", temperatura2m = "°C"),
                Current(tiempo = "", intervalo = 0, temperatura2m = 0F)
                )
        }
    }
}