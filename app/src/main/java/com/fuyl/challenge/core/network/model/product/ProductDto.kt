package com.fuyl.challenge.core.network.model.product

import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    val id: String,
    val title: String,
    val description: String,
    val thumbnail: String,
)
