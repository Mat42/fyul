package com.fuyl.challenge.core.network.model

import com.fuyl.challenge.core.network.model.product.ProductDto
import kotlinx.serialization.Serializable

@Serializable
data class ProductsResponse(
    val products: List<ProductDto>,
    val total: Int,
    val skip: Int,
    val limit: Int
)
