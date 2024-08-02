package com.chinmay.wireline.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.chinmay.wireline.data.local.PexelEntity
import com.chinmay.wireline.data.mappers.toPexel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class PexelViewModel @Inject constructor(
    pager: Pager<Int, PexelEntity>
): ViewModel() {

    val pexelPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toPexel() }
        }
        .cachedIn(viewModelScope)
}
