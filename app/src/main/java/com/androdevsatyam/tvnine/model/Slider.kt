package com.androdevsatyam.tvnine.model

data class Slider(
    val category_name: String,
    val category_name_english: String,
    val episode_no: Int,
    val format: String,
    val id: Int,
    val is_vidgyor: Boolean,
    val mobile_featured_image: String,
    val season_no: Int,
    val short_headline: String,
    val show_details_available: Boolean,
    val title: Title,
    val web_featured_image: String
)