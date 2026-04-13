package com.fuyl.challenge.domain.product.usecase

import androidx.paging.PagingData
import com.fuyl.challenge.domain.product.model.Product
import com.fuyl.challenge.domain.product.repository.ProductRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetProductsUseCaseTest {

    private lateinit var repository: ProductRepository
    private lateinit var getProductsUseCase: GetProductsUseCase

    @Before
    fun setup() {
        repository = mockk()
        getProductsUseCase = GetProductsUseCase(repository)
    }

    @Test
    fun `invoke should call repository getProducts`() {
        val pagingData = PagingData.from(listOf<Product>())
        val flow = flowOf(pagingData)
        every { repository.getProducts() } returns flow

        val result = getProductsUseCase()

        assertEquals(flow, result)
        verify(exactly = 1) { repository.getProducts() }
    }
}
