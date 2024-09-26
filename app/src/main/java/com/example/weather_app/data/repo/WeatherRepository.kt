package com.example.weather_app.data.repo

import com.example.weather_app.data.api.OpenWeatherApi
import com.example.weather_app.data.utils.Resource
import com.example.weather_app.data.vo.WeatherResultVO
import com.example.weather_app.domain.model.WeatherResultDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent

class WeatherRepository2(
    private val openWeatherApi: OpenWeatherApi,
) : KoinComponent, WeatherRepositoryInterface {

    override suspend fun fetchWeather(city: String): Flow<Resource<WeatherResultDomain?>> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchWeather(
        lat: Double,
        lon: Double
    ): Flow<Resource<WeatherResultDomain?>> {
        return flow {
            emit(Resource.Loading())
            val response: WeatherResultVO = openWeatherApi.fetchWeather(lat, lon)
            val domain = response.toDomain()

            emit(Resource.Success(domain))
        }
    }
}