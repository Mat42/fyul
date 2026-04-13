package com.fuyl.challenge.data.product.mapper

import com.fuyl.challenge.core.network.model.product.ProductDto
import org.junit.Assert.assertEquals
import org.junit.Test

class ProductMapperTest {

    @Test
    fun `toDomain should map ProductDto to Product correctly`() {
        val dto = ProductDto(
            id = "1",
            title = "Test Product",
            description = "Test Description",
            thumbnail = "https://example.com/image.jpg"
        )

        val domain = dto.toDomain()

        assertEquals(dto.id, domain.id)
        assertEquals(dto.title, domain.title)
        assertEquals(dto.description, domain.description)
        assertEquals(dto.thumbnail, domain.thumbnail)
    }
}
