package com.androdevsatyam.tvnine.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androdevsatyam.tvnine.repository.RepositorySlider

class SliderViewModelFactory(private val repositorySlider: RepositorySlider) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return SliderViewModel(repositorySlider) as T
    }
}