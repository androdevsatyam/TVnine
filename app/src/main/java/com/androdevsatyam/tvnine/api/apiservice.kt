package com.androdevsatyam.tvnine.api

import com.androdevsatyam.tvnine.model.DashEpisodesModel
import com.androdevsatyam.tvnine.model.DashEpisodesModelItem
import com.androdevsatyam.tvnine.model.SliderData
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("v2/homepage-top-slider-new")
    suspend fun getSlider(): Response<SliderData>

    @GET("v1/show-episode-up-next")
    suspend fun getEpisodes(): Response<DashEpisodesModel>
}