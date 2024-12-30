package com.example.appclima.presenter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appclima.domain.model.LugarModel
import com.example.appclima.domain.usecase.GetLugaresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LugarViewModel @Inject constructor(
    private val getLugaresUseCase: GetLugaresUseCase
): ViewModel() {
    val ldLugarModel = MutableLiveData<LugarModel>()
    //var getLugaresUseCase = GetLugaresUseCase()

    fun onCreate(latitud:String,longitud:String){
        viewModelScope.launch {
            val resp = getLugaresUseCase(latitud,longitud)
            if(resp.fecha.isNotEmpty()){
                ldLugarModel.postValue(LugarModel(latitud,longitud,resp.temperatura,resp.fecha))
            }
            else{
                ldLugarModel.postValue(LugarModel("ND","ND",0F,"ND"))
            }
        }
    }
}