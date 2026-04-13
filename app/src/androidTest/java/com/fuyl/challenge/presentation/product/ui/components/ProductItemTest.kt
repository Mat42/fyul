package com.fuyl.challenge.presentation.product.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.fuyl.challenge.presentation.product.model.ProductUiModel
import org.junit.Rule
import org.junit.Test

class ProductItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun productItem_displaysCorrectContent() {
        val product = ProductUiModel(
            id = "1",
            title = "Test Product",
            description = "Test Description",
            thumbnail = "https://example.com/image.jpg"
        )

        composeTestRule.setContent {
            ProductItem(product = product)
        }

        composeTestRule.onNodeWithText("Test Product").assertIsDisplayed()
        composeTestRule.onNodeWithText("Test Description").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Test Product").assertIsDisplayed()
    }
}
