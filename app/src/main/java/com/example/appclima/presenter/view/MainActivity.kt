package com.example.appclima.presenter.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.appclima.R
import com.example.appclima.dataaccess.LugarDAO
import com.example.appclima.databinding.ActivityMainBinding
import com.example.appclima.domain.model.LugarModel
import com.example.appclima.domain.repository.local.BdRepository
import com.example.appclima.presenter.viewmodel.LugarViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),OnMapReadyCallback,OnMyLocationButtonClickListener,
GoogleMap.OnMyLocationClickListener,OnMapLongClickListener{
    private lateinit var binding: ActivityMainBinding
    private lateinit var lugarDAO: LugarDAO
    private lateinit var db : BdRepository
    private lateinit var map:GoogleMap
    private val lugarViewModel : LugarViewModel by viewModels()
    companion object{const val CODE_LOCATION = 0}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lugarDAO = LugarDAO(this)
        db = BdRepository(lugarDAO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        llenarFragmento()

        lugarViewModel.ldLugarModel.observe(this, Observer {
            val ok = db.insertarDatos(it.Latitud.toString(),it.Longitud.toString(),it.temp,it.fecha)
            if(ok)
            {
                Toast.makeText(this,"Guardado: ${it.temp} grados",Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this,"Error al registrar",Toast.LENGTH_LONG).show()
            }
        })

        binding.btnBuscar.setOnClickListener{
            val intentoVer = Intent(this,LugarActivity::class.java)
            startActivity(intentoVer)
        }
    }

    private fun llenarFragmento(){
        val mapFrag = supportFragmentManager.findFragmentById(R.id.mapas) as SupportMapFragment
        mapFrag.getMapAsync(this)

    }

    override fun onMapReady(mapaCreado: GoogleMap) {
        map = mapaCreado
        crearMarcador()
        map.setOnMyLocationButtonClickListener(this)
        map.setOnMyLocationClickListener(this)
        map.setOnMapLongClickListener(this)
        habilitarLocalizacion()
    }

    private fun crearMarcador(){
        val coordenadas = LatLng(-16.51107756596061, -68.15330898228001)
        val marker = MarkerOptions().position(coordenadas)
        map.addMarker(marker)
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(coordenadas,18f),3000,null)
    }

    private fun permisos() = ContextCompat.checkSelfPermission(this,
            Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

    private fun habilitarLocalizacion(){
        if(!::map.isInitialized) return
        if(permisos()){
            map.isMyLocationEnabled = true
        }
        else{
            requestPermiso()
        }
    }
    private fun requestPermiso(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)){
            Toast.makeText(this,"Aceptar permisos",Toast.LENGTH_LONG).show()
        }
        else{
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                CODE_LOCATION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            CODE_LOCATION -> if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                map.isBuildingsEnabled = true
            }
            else{
                Toast.makeText(this,"Aceptar permisos",Toast.LENGTH_LONG).show()
            }
            else -> {}
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        if(!::map.isInitialized) return
        if(!permisos()){
            map.isMyLocationEnabled = false
            Toast.makeText(this,"Aceptar permisos",Toast.LENGTH_LONG).show()
        }
    }

    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(this,"Bolivia",Toast.LENGTH_LONG).show()
        return true
    }

    override fun onMyLocationClick(p0: Location) {
        Toast.makeText(this,"${p0.latitude} y ${p0.longitude}",Toast.LENGTH_LONG).show()
    }

    override fun onMapLongClick(p0: LatLng) {
        lugarViewModel.onCreate(p0.latitude.toString(),p0.longitude.toString())
    }
}