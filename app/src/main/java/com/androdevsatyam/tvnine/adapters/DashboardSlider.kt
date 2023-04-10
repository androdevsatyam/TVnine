package com.androdevsatyam.tvnine.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.androdevsatyam.tvnine.R
import com.androdevsatyam.tvnine.model.Slider
import com.squareup.picasso.Picasso

class DashboardSlider(private var context: Context) :
    RecyclerView.Adapter<DashboardSlider.DashboardViewHolder>() {

    val TAG = "DashboardSlider"

    private var slidelive: List<Slider>? = null

    fun setSlideData(slidelive: List<Slider>) {
        this.slidelive = slidelive
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        return DashboardViewHolder(
            LayoutInflater.from(context).inflate(R.layout.slide_items, parent, false)
        )
    }

    override fun getItemCount(): Int {
        if (slidelive == null)
            return 0
        else
            return slidelive?.size!!
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        try {
            Picasso.get().load(slidelive!![position].web_featured_image).placeholder(R.drawable.ic_launcher_foreground).into(holder.img)
        } catch (e: Exception) {
            Log.d(TAG, "onBindViewHolder: ${e.message}")
        }
    }

    class DashboardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img: ImageView = itemView.findViewById(R.id.image)
    }

}