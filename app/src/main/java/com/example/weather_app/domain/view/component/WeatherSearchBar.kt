package com.example.weather_app.domain.view.component

import androidx.compose.runtime.Composable

@Composable
fun WeatherSearchBar(
    searchText: String,
    placeholderText: String = "",
    onSearchTextChanged: (String) -> Unit = {},
    onClearClick: () -> Unit = {}
) {
}