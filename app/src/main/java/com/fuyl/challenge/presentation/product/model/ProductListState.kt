package com.fuyl.challenge.presentation.product.model

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class ProductListState(
    val products: Flow<PagingData<ProductUiModel>> = emptyFlow()
)
