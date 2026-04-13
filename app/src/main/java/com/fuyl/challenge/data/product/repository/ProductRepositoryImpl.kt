package com.fuyl.challenge.data.product.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.fuyl.challenge.core.network.api.DummyProductApi
import com.fuyl.challenge.data.product.paging.ProductPagingSource
import com.fuyl.challenge.domain.product.model.Product
import com.fuyl.challenge.domain.product.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api: DummyProductApi
) : ProductRepository {

    override fun getProducts(): Flow<PagingData<Product>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                ProductPagingSource(api)
            }
        ).flow
    }
}