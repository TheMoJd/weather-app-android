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

    override suspend fun fetchWeather(city: String): Flow<Resource<WeatherResultDomain?>> =
        fetchWeatherForAll(lat = null, lon = null, city = city)

    override suspend fun fetchWeather(lat: Double, lon: Double): Flow<Resource<WeatherResultDomain?>> =
        fetchWeatherForAll(lat = lat, lon = lon, city = null)

    private suspend fun fetchWeatherForAll(lat: Double?, lon: Double?, city:String?) : Flow<Resource<WeatherResultDomain?>> {
        return flow {
            emit(Resource.Loading())

            val response: WeatherResultVO? = if (city != null) {
                openWeatherApi.fetchWeather(city)
            } else if (lat != null && lon != null) {
                openWeatherApi.fetchWeather(lat, lon)
            } else {
                null
            }
            val domain:  WeatherResultDomain? =  response?.toDomain()
            if (domain != null) {
                emit(Resource.Success(domain))
            } else {
                emit(Resource.Error(Exception("NO DATA")))
            }
        }
    }
}