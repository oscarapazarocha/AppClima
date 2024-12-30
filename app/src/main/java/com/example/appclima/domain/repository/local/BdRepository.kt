package com.example.appclima.domain.repository.local

import com.example.appclima.dataaccess.LugarDAO
import com.example.appclima.dataaccess.entities.LugarEntity

class BdRepository(dataAccessDAO: LugarDAO) {

    private val lugarDAO = dataAccessDAO

    fun insertarDatos(latitud:String,longitud:String,temp:Float,fecha:String):Boolean
        = lugarDAO.insertarLugar(latitud,longitud,temp,fecha)

    fun getDatos():ArrayList<LugarEntity>
        = lugarDAO.getLugares()
}