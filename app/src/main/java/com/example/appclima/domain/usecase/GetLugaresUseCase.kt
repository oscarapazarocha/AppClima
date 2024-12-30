package com.example.appclima.domain.usecase

import com.example.appclima.domain.mapper.AppMapper
import com.example.appclima.domain.model.DatosResponse
import com.example.appclima.domain.repository.remote.ApiRepository
import javax.inject.Inject

class GetLugaresUseCase @Inject constructor(
    private val repositorio : ApiRepository,
    private val mapper : AppMapper
)  {
    //private val repositorio = ApiRepository()
    //private val mapper = AppMapper()
    suspend operator fun invoke(lat: String,  lon: String): DatosResponse {
        val apiResp = repositorio.getDatos(lat, lon)
        return mapper.ResponseMapper(apiResp)
    }
}