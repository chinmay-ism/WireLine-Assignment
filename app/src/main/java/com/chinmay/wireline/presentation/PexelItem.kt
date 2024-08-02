package com.chinmay.wireline.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.chinmay.wireline.domain.Pexel

@Composable
fun PexelItem(
    pexel: Pexel,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center){
        AsyncImage(
            model = pexel.smallSrc,
            contentDescription = pexel.alt,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .fillMaxSize()
        )
    }


}
