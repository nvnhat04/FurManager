package com.example.furmanager.ui.components

import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
data class Product(
    val title: String,
    val price: String,
    val imageUrl: Int
    )
data class BannerData(
    val title: String,
    val imageRes: Int
)