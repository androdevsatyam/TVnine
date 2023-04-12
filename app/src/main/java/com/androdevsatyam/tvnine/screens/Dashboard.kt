package com.androdevsatyam.tvnine.screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androdevsatyam.tvnine.R
import com.androdevsatyam.tvnine.adapters.DashboardEpisodes
import com.androdevsatyam.tvnine.adapters.DashboardSlider
import com.androdevsatyam.tvnine.api.ApiRetro
import com.androdevsatyam.tvnine.helpers.Global
import com.androdevsatyam.tvnine.model.DashEpisodesModelItem
import com.androdevsatyam.tvnine.repository.RepositorySlider
import com.androdevsatyam.tvnine.viewmodel.SliderViewModel
import com.androdevsatyam.tvnine.viewmodel.SliderViewModelFactory
import com.google.android.ads.nativetemplates.rvadapter.AdmobNativeAdAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.jackandphantom.carouselrecyclerview.CarouselRecyclerview


class Dashboard : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var signout: FloatingActionButton
    lateinit var slideList: CarouselRecyclerview
    lateinit var episodeList: RecyclerView
    lateinit var loader: ProgressBar
    val TAG = "DashBoard"
    lateinit var dashViewModel: SliderViewModel
    lateinit var analytics: FirebaseAnalytics
    lateinit var bundle: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        auth = FirebaseAuth.getInstance()
        analytics = Firebase.analytics
        signout = findViewById(R.id.signout)

        supportActionBar?.title = "Dashboard"

        val apiService = ApiRetro().getConnection()
        val repositorySlider = RepositorySlider(apiService)
        slideList = findViewById(R.id.slider)
        episodeList = findViewById(R.id.episodes)
        loader = findViewById(R.id.progress_bar)

        episodeList.layoutManager = LinearLayoutManager(this)

        val sliderAdapter = DashboardSlider(this)
        val slidesAds = AdmobNativeAdAdapter.Builder.with(
            resources.getString(R.string.native_id), sliderAdapter, "small"
        ).adItemInterval(2).build()
        slideList.adapter = slidesAds

        slideList.apply {
            set3DItem(true)
            setAlpha(true)
            setInfinite(true)
            setHasFixedSize(true)
        }

        val episodeAdapter = DashboardEpisodes(this, ::episodeClick)
        val episodeAds = AdmobNativeAdAdapter.Builder.with(
            resources.getString(R.string.banner_id), episodeAdapter, "small"
        ).adItemInterval(2).build()
        episodeList.adapter = episodeAds

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
            loader.visibility = View.GONE
            episodeAdapter.notifyDataSetChanged()
        }

        signout.setOnClickListener {
            auth.signOut()
            bundle = Bundle()
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "SignOut")
            Global.logs(FirebaseAnalytics.Event.BEGIN_CHECKOUT, bundle, analytics)
            intent = Intent(this, Splash::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun episodeClick(episo: DashEpisodesModelItem) {
        bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.LOCATION, "Dashboard")
        bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, "Choosed:" + episo.episode_no)
        Global.logs(FirebaseAnalytics.Event.SELECT_CONTENT, bundle, analytics)

        val intent = Intent(this, Details::class.java)

        val bundle = Bundle()
        bundle.putString("episodes", Gson().toJson(episo))
        intent.putExtra("data", bundle)
        startActivity(intent)
    }
}
