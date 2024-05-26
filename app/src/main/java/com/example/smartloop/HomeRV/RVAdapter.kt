package com.example.smartloop.HomeRV

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.smartloop.R

class RVAdapter(val list: ArrayList<RVModel>, val images: Context) : Adapter<OurVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OurVH {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.course_sample_rv, parent, false)
        return OurVH(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: OurVH, position: Int) {
        holder.img.setImageResource(list.get(position).image)
        holder.tv.text = list.get(position).tv
    }


}

class OurVH(view: View) : RecyclerView.ViewHolder(view) {

    val img = view.findViewById<ImageView>(R.id.img)
    val tv = view.findViewById<TextView>(R.id.tv_course)

}
