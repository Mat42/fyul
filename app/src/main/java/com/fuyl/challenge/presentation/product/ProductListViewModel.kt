package com.fuyl.challenge.presentation.product

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.fuyl.challenge.core.common.base.BaseViewModel
import com.fuyl.challenge.domain.product.usecase.GetProductsUseCase
import com.fuyl.challenge.presentation.product.model.ProductListEvent
import com.fuyl.challenge.presentation.product.model.ProductListState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : BaseViewModel<ProductListState, ProductListEvent>() {

    init {
        loadProducts()
    }

    override fun initialState(): ProductListState = ProductListState()

    override fun onEvent(event: ProductListEvent) {
        when (event) {
            ProductListEvent.Refresh -> loadProducts()
        }
    }

    private fun loadProducts() {
        updateState { 
            it.copy(products = getProductsUseCase().cachedIn(viewModelScope))
        }
    }
}
