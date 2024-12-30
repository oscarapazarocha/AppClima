package com.example.appclima.dataaccess

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.appclima.dataaccess.entities.LugarEntity
import com.example.appclima.domain.model.DatosResponse


class LugarDAO(contexto: Context):SQLiteOpenHelper(contexto,"ddp.db",null,1) {
    companion object{
        const val TABLA_LUGAR="lugar"
        const val ID="id"
        const val LATITUD="latitud"
        const val LONGITUD="longitud"
        const val TEMPERATURA="temperatura"
        const val FECHA="fecha"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        var sql="CREATE TABLE $TABLA_LUGAR ($ID INTEGER PRIMARY KEY AUTOINCREMENT,$LATITUD TEXT,$LONGITUD TEXT,$TEMPERATURA TEXT,$FECHA TEXT)"
        db!!.execSQL(sql)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val sql="Base de Datos"
        print(sql)
    }
    fun insertarLugar(latitud:String,longitud:String,temp:Float,fecha:String):Boolean{
        val dts = ContentValues()
        var insertado=true
        dts.put(LATITUD,latitud)
        dts.put(LONGITUD,longitud)
        dts.put(TEMPERATURA,temp)
        dts.put(FECHA,fecha)
        val ddp = this.writableDatabase
        try{
            ddp.insert(TABLA_LUGAR,null,dts)
        }
        catch (e: IllegalArgumentException)
        {
            insertado=false
        }
        ddp.close()
        return insertado
    }
    fun getLugares():ArrayList<LugarEntity>{
        val listaLugares : ArrayList<LugarEntity> = ArrayList()
        var lugar:LugarEntity
        val ddp = readableDatabase
        val sql = "SELECT * FROM $TABLA_LUGAR"
        val cursor = ddp.rawQuery(sql,null)
        if (cursor.moveToFirst())
        {
            do{
                lugar = LugarEntity(cursor.getInt(0),cursor.getString(1),
                    cursor.getString(2),cursor.getFloat(3),cursor.getString(4))
                listaLugares.add(lugar)
            }while (cursor.moveToNext())
        }
        cursor.close()
        ddp.close()
        return listaLugares
    }
}