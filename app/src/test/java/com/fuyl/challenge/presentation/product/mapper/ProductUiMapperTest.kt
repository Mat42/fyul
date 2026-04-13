package com.fuyl.challenge.presentation.product.mapper

import com.fuyl.challenge.domain.product.model.Product
import org.junit.Assert.assertEquals
import org.junit.Test

class ProductUiMapperTest {

    @Test
    fun `toUiModel should map Product to ProductUiModel correctly`() {
        val domain = Product(
            id = "1",
            title = "Test Product",
            description = "Test Description",
            thumbnail = "https://example.com/image.jpg"
        )

        val uiModel = domain.toUiModel()

        assertEquals(domain.id, uiModel.id)
        assertEquals(domain.title, uiModel.title)
        assertEquals(domain.description, uiModel.description)
        assertEquals(domain.thumbnail, uiModel.thumbnail)
    }
}
