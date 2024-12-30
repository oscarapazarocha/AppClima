package com.example.appclima.core
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.appclima.R
import com.example.appclima.dataaccess.entities.LugarEntity
import com.example.appclima.databinding.ItemLugarBinding

class LugarViewHolder(vista: View):ViewHolder(vista) {
    private val binding = ItemLugarBinding.bind(vista)
    fun render(lugar: LugarEntity, onclickListener: (LugarEntity) -> Unit) {
        //val lugaruri = Uri.parse(lugar.temperatura)
        //Glide.with(binding.rvvalorimg.context).load(lugar.valor).into(binding.rvvalorimg)
        //binding.rvvalorimg.setImageURI(lugaruri)
        if(lugar.temperatura < 10F ) {
            binding.rvvalorimg.setImageResource(R.drawable.frio)
        }
        else if (lugar.temperatura <20F && lugar.temperatura >= 10F) {
            binding.rvvalorimg.setImageResource(R.drawable.bien)
        }
        else{
            binding.rvvalorimg.setImageResource(R.drawable.calor)
        }
        binding.rvlatitud.text = lugar.latitud
        binding.rvlongitud.text = lugar.longiutd
        binding.rvtemp.text = lugar.temperatura.toString()
        binding.rvfecha.text = lugar.fecha
        itemView.setOnClickListener {
            onclickListener(lugar)
        }
    }
}