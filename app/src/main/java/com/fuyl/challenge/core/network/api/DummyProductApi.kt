package com.fuyl.challenge.core.network.api

import com.fuyl.challenge.core.network.model.ProductsResponse

interface DummyProductApi {
    suspend fun getProducts(skip: Int, limit: Int): ProductsResponse
}
