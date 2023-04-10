package com.androdevsatyam.tvnine.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androdevsatyam.tvnine.api.ApiService
import com.androdevsatyam.tvnine.model.DashEpisodesModel
import com.androdevsatyam.tvnine.model.SliderData

class RepositorySlider(private val apiService: ApiService) {

    private val slideData = MutableLiveData<SliderData>()
    private val episodeData = MutableLiveData<DashEpisodesModel>()

    val slides: LiveData<SliderData>
        get() = slideData
    val episodes: LiveData<DashEpisodesModel>
        get() = episodeData

    suspend fun getSliderRepo() {
        val result = apiService.getSlider()
        if (result.body() != null) {
            slideData.postValue(result.body())
        }
    }

    suspend fun getEpisode() {
        val result = apiService.getEpisodes()
        if (result.body() != null) {
            episodeData.postValue(result.body())
        }
    }
}