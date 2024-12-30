package com.example.appclima.domain.mapper

import com.example.appclima.domain.model.DatosResponse
import com.example.appclima.domain.model.LugarResponse
import javax.inject.Inject

class AppMapper @Inject constructor() {
    fun ResponseMapper(respApi:LugarResponse):DatosResponse = DatosResponse(respApi.current.tiempo,respApi.current.temperatura2m)
}