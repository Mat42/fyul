package com.fuyl.challenge.domain.product.repository

import androidx.paging.PagingData
import com.fuyl.challenge.domain.product.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(): Flow<PagingData<Product>>
}
