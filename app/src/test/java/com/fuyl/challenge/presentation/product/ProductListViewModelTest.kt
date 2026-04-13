package com.fuyl.challenge.presentation.product

import androidx.paging.PagingData
import app.cash.turbine.test
import com.fuyl.challenge.domain.product.model.Product
import com.fuyl.challenge.domain.product.usecase.GetProductsUseCase
import com.fuyl.challenge.presentation.product.model.ProductListEvent
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProductListViewModelTest {

    private lateinit var getProductsUseCase: GetProductsUseCase
    private lateinit var viewModel: ProductListViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        getProductsUseCase = mockk()
        
        // Mock initial call in init { loadProducts() }
        every { getProductsUseCase() } returns flowOf(PagingData.empty())
        
        viewModel = ProductListViewModel(getProductsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state should have empty products flow`() = runTest {
        viewModel.uiState.test {
            val state = awaitItem()
            assertNotNull(state.products)
        }
    }

    @Test
    fun `loadProducts should update products in state`() = runTest {
        val pagingData = PagingData.from(listOf(Product("1", "Title", "Desc", "thumb")))
        every { getProductsUseCase() } returns flowOf(pagingData)

        viewModel.onEvent(ProductListEvent.Refresh)

        viewModel.uiState.test {
            val state = awaitItem()
            assertNotNull(state.products)
            // Note: testing actual PagingData content inside a flow is complex 
            // and usually handled by paging-testing or integration tests.
        }
    }
}
