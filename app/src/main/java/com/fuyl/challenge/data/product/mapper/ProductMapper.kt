package com.fuyl.challenge.data.product.mapper

import com.fuyl.challenge.core.network.model.product.ProductDto
import com.fuyl.challenge.domain.product.model.Product

fun ProductDto.toDomain(): Product {
    return Product(
        id = id,
        title = title,
        description = description,
        thumbnail = thumbnail
    )
}
