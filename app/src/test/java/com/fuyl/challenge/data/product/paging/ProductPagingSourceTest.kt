package com.fuyl.challenge.data.product.paging

import androidx.paging.PagingSource
import com.fuyl.challenge.core.model.exception.UnknownNetworkException
import com.fuyl.challenge.core.network.api.DummyProductApi
import com.fuyl.challenge.core.network.model.ProductsResponse
import com.fuyl.challenge.core.network.model.product.ProductDto
import com.fuyl.challenge.domain.product.model.Product
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ProductPagingSourceTest {

    private lateinit var api: DummyProductApi
    private lateinit var pagingSource: ProductPagingSource

    @Before
    fun setup() {
        api = mockk()
        pagingSource = ProductPagingSource(api)
    }

    @Test
    fun `load returns success on api success`() = runTest {
        val productDtos = listOf(
            ProductDto("1", "Product 1", "Desc 1", "thumb1"),
            ProductDto("2", "Product 2", "Desc 2", "thumb2")
        )
        val response = ProductsResponse(
            products = productDtos,
            total = 100,
            skip = 0,
            limit = 2
        )

        coEvery { api.getProducts(any(), any()) } returns response

        val expectedResult = PagingSource.LoadResult.Page(
            data = listOf(
                Product("1", "Product 1", "Desc 1", "thumb1"),
                Product("2", "Product 2", "Desc 2", "thumb2")
            ),
            prevKey = null,
            nextKey = 2
        )

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        assertEquals(expectedResult, result)
    }

    @Test
    fun `load returns error on api failure`() = runTest {
        val exceptionMessage = "Network Error"
        val exception = RuntimeException(exceptionMessage)
        coEvery { api.getProducts(any(), any()) } throws exception

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        assertTrue(result is PagingSource.LoadResult.Error)
        val error = result as PagingSource.LoadResult.Error
        assertTrue(error.throwable is UnknownNetworkException)
        assertEquals(exceptionMessage, error.throwable.message)
    }
}
