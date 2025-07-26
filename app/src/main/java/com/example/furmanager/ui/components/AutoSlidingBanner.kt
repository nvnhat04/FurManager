package com.example.furmanager.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.navigation.compose.rememberNavController
import com.example.furmanager.R
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AutoSlidingBanner(banners: List<BannerData>) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    // Auto scroll every 3 seconds
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            val nextPage = (pagerState.currentPage + 1) % banners.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        HorizontalPager(
            count = banners.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .padding(5.dp)
        ) { page ->
            BannerCard(banners[page])
        }

        Spacer(modifier = Modifier.height(8.dp))

        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = Color.Black,
            inactiveColor = Color.LightGray,
            indicatorWidth = 8.dp,
            spacing = 6.dp
        )
    }
}
@Preview
@Composable
fun showSlide1(){
    val banners = listOf(
        BannerData("Banner 1", R.drawable.sofa),
        BannerData("Banner 2", R.drawable.table),
        BannerData("Banner 3", R.drawable.sofa)
    )

    AutoSlidingBanner(banners = banners)
}
