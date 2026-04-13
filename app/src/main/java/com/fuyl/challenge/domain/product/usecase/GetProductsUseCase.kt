package com.fuyl.challenge.domain.product.usecase

import androidx.paging.PagingData
import com.fuyl.challenge.domain.product.model.Product
import com.fuyl.challenge.domain.product.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(): Flow<PagingData<Product>> {
        return repository.getProducts()
    }
}
