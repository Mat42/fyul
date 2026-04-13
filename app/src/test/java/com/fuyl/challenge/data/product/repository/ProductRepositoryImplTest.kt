package com.fuyl.challenge.data.product.repository

import com.fuyl.challenge.core.network.api.DummyProductApi
import io.mockk.mockk
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class ProductRepositoryImplTest {

    private lateinit var api: DummyProductApi
    private lateinit var repository: ProductRepositoryImpl

    @Before
    fun setup() {
        api = mockk()
        repository = ProductRepositoryImpl(api)
    }

    @Test
    fun `getProducts should return paging data flow`() {
        val result = repository.getProducts()
        assertNotNull(result)
    }
}
