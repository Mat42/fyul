package com.fuyl.challenge.presentation.product.model

sealed interface ProductListEvent {
    data object Refresh : ProductListEvent
}