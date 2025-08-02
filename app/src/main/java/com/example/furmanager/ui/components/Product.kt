package com.example.furmanager.ui.components

import com.google.accompanist.pager.ExperimentalPagerApi
import com.example.furmanager.R
@OptIn(ExperimentalPagerApi::class)
data class Product(
    val id: String = "",
    val title: String = "",
    val price: String = "",
    val imageUrl: Int = R.drawable.sofa
    )
data class BannerData(
    val title: String = "",
    val imageRes: Int = R.drawable.sofa
)