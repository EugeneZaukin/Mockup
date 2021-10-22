package com.eugene.mockup.viewModel

import com.eugene.mockup.model.Valute

sealed class AppState {
    data class Success (val data: List<Valute>?) : AppState();
    data class Error (val error: Throwable) : AppState();
    object Loading : AppState();
}