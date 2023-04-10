package com.androdevsatyam.tvnine.screens

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.androdevsatyam.tvnine.R
import com.androdevsatyam.tvnine.model.DashEpisodesModelItem
import com.bumptech.glide.Glide
import com.google.gson.Gson

class Details : AppCompatActivity() {
    val TAG="Details"

    lateinit var title:TextView
    lateinit var category:TextView
    lateinit var format:TextView
    lateinit var episodenum:TextView
    lateinit var shortHeadline:TextView
    lateinit var episodeBanner:ImageView

    lateinit var episodeItem:DashEpisodesModelItem


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        supportActionBar?.title="Detail Page"
        initView()
        val data=intent?.getBundleExtra("data")?.get("episodes")
        episodeItem=Gson().fromJson(data.toString(),DashEpisodesModelItem::class.java)

        Glide.with(this).load(episodeItem.web_featured_image).into(episodeBanner)

        title.text="Title:"+episodeItem.title.rendered
        category.text="Cat:"+episodeItem.category_name+"\n"+episodeItem.category_name_english
        format.text="Format:"+episodeItem.format
        episodenum.text="Episodes:"+episodeItem.episode_no.toString()
        shortHeadline.text="Short:"+episodeItem.short_headline

    }

    private fun initView() {
        episodeBanner=findViewById(R.id.episode_banner)
        title=findViewById(R.id.title)
        category=findViewById(R.id.category)
        format=findViewById(R.id.format)
        episodenum=findViewById(R.id.episode_num)
        shortHeadline=findViewById(R.id.short_headline)
    }
}