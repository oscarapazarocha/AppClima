package com.example.appclima.core

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appclima.R
import com.example.appclima.dataaccess.entities.LugarEntity

class LugarAdapter(private val listalugares:ArrayList<LugarEntity>, private val onclicklistener:(LugarEntity)->Unit):RecyclerView.Adapter<LugarViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LugarViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return LugarViewHolder(layoutInflater.inflate(
            R.layout.item_lugar,parent,false
        ))
    }

    override fun getItemCount(): Int {
        return listalugares.size
    }

    override fun onBindViewHolder(holder: LugarViewHolder, position: Int) {
        val lugar= listalugares[position]
        holder.render(lugar,onclicklistener)
    }
}

