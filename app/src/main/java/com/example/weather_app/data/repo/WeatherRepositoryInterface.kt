package com.example.weather_app.data.repo

import com.example.weather_app.domain.model.WeatherResultDomain
import kotlinx.coroutines.flow.Flow
import com.example.weather_app.data.utils.Resource


interface WeatherRepositoryInterface {
    suspend fun fetchWeather(city: String): Flow<Resource<WeatherResultDomain?>>
    suspend fun fetchWeather(lat: Double, lon: Double): Flow<Resource<WeatherResultDomain?>>
}