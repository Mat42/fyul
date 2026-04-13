package com.fuyl.challenge.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fuyl.challenge.presentation.product.ui.screens.ProductListScreen
import com.fuyl.challenge.presentation.product.ProductListViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.ProductList
    ) {
        composable<Screen.ProductList> {
            val viewModel: ProductListViewModel = hiltViewModel()
            val state by viewModel.uiState.collectAsStateWithLifecycle()
            ProductListScreen(
                state = state,
            )
        }
    }
}
