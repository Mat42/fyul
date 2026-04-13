package com.fuyl.challenge.presentation.product.model

import androidx.paging.PagingData
import com.fuyl.challenge.domain.product.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class ProductListState(
    val products: Flow<PagingData<Product>> = emptyFlow()
)