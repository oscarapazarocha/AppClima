package com.example.appclima.presenter.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appclima.core.LugarAdapter
import com.example.appclima.dataaccess.LugarDAO
import com.example.appclima.dataaccess.entities.LugarEntity
import com.example.appclima.databinding.ActivityLugarBinding
import com.example.appclima.domain.repository.local.BdRepository

class LugarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLugarBinding
    private lateinit var lugarDAO: LugarDAO
    private lateinit var db: BdRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLugarBinding.inflate(layoutInflater)
        setContentView(binding.activitylugar)
        lugarDAO = LugarDAO(this)
        db = BdRepository(lugarDAO)
        llenarListaLugares()
    }
    private fun llenarListaLugares(){
        val manager = LinearLayoutManager(this)
        val decorador = DividerItemDecoration(this,manager.orientation)
        val rvlugares = binding.rvverlistalugares
        rvlugares.layoutManager = manager
        rvlugares.adapter = LugarAdapter(db.getDatos()){lugar -> datoElegido(lugar)}
        rvlugares.addItemDecoration((decorador))
    }
    private fun datoElegido(dato: LugarEntity){
        Toast.makeText(this,"La temperatura es : ${dato.temperatura}",Toast.LENGTH_LONG).show()
    }
}