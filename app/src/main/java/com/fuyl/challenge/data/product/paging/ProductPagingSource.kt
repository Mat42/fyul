package com.fuyl.challenge.data.product.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fuyl.challenge.core.network.api.DummyProductApi
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
        return try {
            val response = api.getProducts(skip = position, limit = params.loadSize)
            val products = response.products.map { it.toDomain() }

            LoadResult.Page(
                data = products,
                prevKey = if (position == 0) null else position - params.loadSize,
                nextKey = if (products.isEmpty() || response.total <= position + params.loadSize) null else position + params.loadSize
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
