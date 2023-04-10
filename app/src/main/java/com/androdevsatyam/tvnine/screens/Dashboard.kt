package com.androdevsatyam.tvnine.screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androdevsatyam.tvnine.R
import com.androdevsatyam.tvnine.adapters.DashboardEpisodes
import com.androdevsatyam.tvnine.adapters.DashboardSlider
import com.androdevsatyam.tvnine.api.ApiRetro
import com.androdevsatyam.tvnine.repository.RepositorySlider
import com.androdevsatyam.tvnine.viewmodel.SliderViewModel
import com.androdevsatyam.tvnine.viewmodel.SliderViewModelFactory
import com.google.android.ads.nativetemplates.rvadapter.AdmobNativeAdAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth

class Dashboard : AppCompatActivity() {


    lateinit var auth: FirebaseAuth
    lateinit var signout: FloatingActionButton
    lateinit var slideList: RecyclerView
    lateinit var episodeList: RecyclerView
    val TAG = "DashBoard"
    lateinit var dashViewModel: SliderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        auth = FirebaseAuth.getInstance()
        signout = findViewById(R.id.signout)

        supportActionBar?.title="Dashboard"

        val apiService = ApiRetro().getConnection()
        val repositorySlider = RepositorySlider(apiService)
        slideList = findViewById(R.id.slider)
        episodeList = findViewById(R.id.episodes)
        slideList.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        slideList.layoutManager = layoutManager
        episodeList.layoutManager = LinearLayoutManager(this)

        val sliderAdapter = DashboardSlider(this)
        val admobNativeAdAdapter = AdmobNativeAdAdapter.Builder.with(
            resources.getString(R.string.native_id),
            sliderAdapter,
            "medium"   // "medium" it can also used
        ).adItemInterval(2).build()
        slideList.adapter = admobNativeAdAdapter

        val episodeAdapter = DashboardEpisodes(this)
        episodeList.adapter = episodeAdapter

        dashViewModel = ViewModelProvider(this, SliderViewModelFactory(repositorySlider)).get(
            SliderViewModel::class.java
        )

        dashViewModel.slides.observe(this) {
            Log.d(TAG, "Slides: $it")
            sliderAdapter.setSlideData(it.slider)
            sliderAdapter.notifyDataSetChanged()
        }

        dashViewModel.episodes.observe(this) {
            Log.d(TAG, "Episodes: $it")
            episodeAdapter.setSlideData(it)
            episodeAdapter.notifyDataSetChanged()
        }

        signout.setOnClickListener {
            auth.signOut()
            intent = Intent(this, Splash::class.java)
            startActivity(intent)
            finish()
        }


    }
}