package com.example.appclima.domain.model

import com.google.gson.annotations.SerializedName

data class LugarResponse (
    @SerializedName("latitude") var latitud:Float,
    @SerializedName("longitude") var longitud:Float,
    @SerializedName("generationtime_ms") var tiempo:Float,
    @SerializedName("utc_offset_seconds") var utctiempo:Int,
    @SerializedName("timezone") var timezone:String,
    @SerializedName("timezone_abbreviation") var timezoneabb:String,
    @SerializedName("elevation") var elevation:Float,
    @SerializedName("current_units") var current_units:Unidades,
    @SerializedName("current") var current:Current)

data class Unidades(
    @SerializedName("time") var tiempo:String,
    @SerializedName("interval") var intervalo:String,
    @SerializedName("temperature_2m") var temperatura2m:String
)

data class Current(
    @SerializedName("time") var tiempo:String,
    @SerializedName("interval") var intervalo:Int,
    @SerializedName("temperature_2m") var temperatura2m:Float
)

data class DatosResponse(val fecha:String,val temperatura:Float)