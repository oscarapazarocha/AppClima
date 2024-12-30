package com.example.appclima.service

import com.example.appclima.domain.model.LugarResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface IApiService {
    @GET
    suspend fun getDatosApi(@Url url:String): Response<LugarResponse>
}