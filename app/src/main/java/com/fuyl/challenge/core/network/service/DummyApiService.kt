package com.fuyl.challenge.core.network.service

import com.fuyl.challenge.core.di.DummyClient
import com.fuyl.challenge.core.network.api.DummyProductApi
import com.fuyl.challenge.core.network.model.ProductsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class DummyApiService @Inject constructor(
    @DummyClient private val client: HttpClient
): DummyProductApi {

    override suspend fun getProducts(skip: Int, limit: Int): ProductsResponse {
        return client.get("products") {
            parameter("skip", skip)
            parameter("limit", limit)
        }.body()
    }
}
