package com.androdevsatyam.tvnine.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androdevsatyam.tvnine.model.DashEpisodesModel
import com.androdevsatyam.tvnine.model.SliderData
import com.androdevsatyam.tvnine.repository.RepositorySlider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SliderViewModel(private val repositorySlider: RepositorySlider) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repositorySlider.getSliderRepo()
            repositorySlider.getEpisode()
        }
    }

    val slides: LiveData<SliderData>
        get() = repositorySlider.slides

    val episodes: LiveData<DashEpisodesModel>
        get() = repositorySlider.episodes
}