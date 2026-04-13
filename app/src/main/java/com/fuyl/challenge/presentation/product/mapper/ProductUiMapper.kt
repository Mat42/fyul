package com.fuyl.challenge.presentation.product.mapper

import com.fuyl.challenge.domain.product.model.Product
import com.fuyl.challenge.presentation.product.model.ProductUiModel

fun Product.toUiModel(): ProductUiModel {
    return ProductUiModel(
        id = id,
        title = title,
        description = description,
        thumbnail = thumbnail
    )
}
