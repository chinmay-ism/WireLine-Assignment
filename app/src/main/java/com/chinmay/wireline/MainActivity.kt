package com.chinmay.wireline

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.chinmay.wireline.presentation.PexelScreen
import com.chinmay.wireline.presentation.PexelViewModel
import com.chinmay.wireline.ui.theme.WireLineTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WireLineTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val viewModel = hiltViewModel<PexelViewModel>()
                    val pexels = viewModel.pexelPagingFlow.collectAsLazyPagingItems()
                    PexelScreen(pexels = pexels)
                }
            }
        }
    }
}