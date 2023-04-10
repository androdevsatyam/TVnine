package com.androdevsatyam.tvnine.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.androdevsatyam.tvnine.R
import com.androdevsatyam.tvnine.model.DashEpisodesModel
import com.androdevsatyam.tvnine.model.DashEpisodesModelItem
import com.androdevsatyam.tvnine.screens.Details
import com.google.gson.Gson

class DashboardEpisodes(private var context: Context, private var episodeClick:(DashEpisodesModelItem)-> Unit) :
    RecyclerView.Adapter<DashboardEpisodes.EpisodesViewHolder>() {

    private var episodes: DashEpisodesModel? = null

    fun setSlideData(episodes: DashEpisodesModel){
        this.episodes=episodes
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        return EpisodesViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_episodes, parent, false)
        )
    }

    override fun getItemCount(): Int {
        if (episodes == null)
            return 0
        else
            return episodes?.size!!
    }

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        holder.type.text=episodes!![position].type
        holder.slug.text=episodes!![position].slug
        holder.short_head.text=episodes!![position].short_headline
        holder.cat_eng.text=episodes!![position].category_name_english

        holder.row.setOnClickListener{
            val episode=episodes!![holder.absoluteAdapterPosition]
            episodeClick(episode)
        }
    }

    class EpisodesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var row: CardView = itemView.findViewById(R.id.row)
        var type: TextView = itemView.findViewById(R.id.type)
        var slug: TextView = itemView.findViewById(R.id.slug)
        var short_head: TextView = itemView.findViewById(R.id.short_headline)
        var cat_eng: TextView = itemView.findViewById(R.id.cat_eng)
    }
}
