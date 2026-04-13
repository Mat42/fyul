package com.fuyl.challenge.data.product.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fuyl.challenge.core.model.Result
import com.fuyl.challenge.core.network.api.DummyProductApi
import com.fuyl.challenge.core.network.safeApiCall
import com.fuyl.challenge.data.product.mapper.toDomain
import com.fuyl.challenge.domain.product.model.Product

class ProductPagingSource(
    private val api: DummyProductApi
) : PagingSource<Int, Product>() {

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(state.config.pageSize)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(state.config.pageSize)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        val position = params.key ?: 0

        val result = safeApiCall("Failed to fetch products") {
            api.getProducts(skip = position, limit = params.loadSize)
        }

        return when (result) {
            is Result.Success -> {
                val response = result.data
                Log.d("ProductPagingSource", "Response: $response")
                val products = response.products.map { it.toDomain() }
                LoadResult.Page(
                    data = products,
                    prevKey = if (position == 0) null else position - params.loadSize,
                    nextKey = if (products.isEmpty() || response.total <= position + params.loadSize) null else position + params.loadSize
                )
            }
            is Result.Error -> {
                LoadResult.Error(result.exception ?: Exception(result.message))
            }
            is Result.Loading -> {
                LoadResult.Error(IllegalStateException("Unexpected loading state"))
            }
        }
    }
}