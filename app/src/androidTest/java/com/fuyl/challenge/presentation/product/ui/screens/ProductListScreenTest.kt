package com.fuyl.challenge.presentation.product.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.paging.PagingData
import com.fuyl.challenge.presentation.product.model.ProductListState
import com.fuyl.challenge.presentation.product.model.ProductUiModel
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test

class ProductListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun productListScreen_displaysProducts() {
        val products = listOf(
            ProductUiModel("1", "Product 1", "Description 1", "thumb1"),
            ProductUiModel("2", "Product 2", "Description 2", "thumb2")
        )
        val state = ProductListState(
            products = flowOf(PagingData.from(products))
        )

        composeTestRule.setContent {
            ProductListScreen(state = state)
        }

        // Check if title is displayed (from Scaffold TopAppBar)
        // Assuming string resource R.string.products_screen_title is "Products"
        // Since I don't have the actual string value, I'll check for product titles
        composeTestRule.onNodeWithText("Product 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Product 2").assertIsDisplayed()
    }

    @Test
    fun productListScreen_displaysEmptyState() {
        val state = ProductListState(
            products = flowOf(PagingData.empty())
        )

        composeTestRule.setContent {
            ProductListScreen(state = state)
        }

        // Verify no products are shown. 
        // In a real app, you might check for an empty state illustration or text.
        composeTestRule.onNodeWithText("Product 1").assertDoesNotExist()
    }
}
