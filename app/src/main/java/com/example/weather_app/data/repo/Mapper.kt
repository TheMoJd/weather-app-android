package com.example.weather_app.data.repo

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.weather_app.data.vo.CoordinateVO
import com.example.weather_app.data.vo.WeatherCityVO
import com.example.weather_app.data.vo.WeatherInfoVO
import com.example.weather_app.data.vo.WeatherItemVO
import com.example.weather_app.data.vo.WeatherResultVO
import com.example.weather_app.domain.*
import com.example.weather_app.domain.model.CoordinateDomain
import com.example.weather_app.domain.model.WeatherCityDomain
import com.example.weather_app.domain.model.WeatherInfoDomain
import com.example.weather_app.domain.model.WeatherItemDomain
import com.example.weather_app.domain.model.WeatherResultDomain
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun WeatherResultVO.toDomain() = WeatherResultDomain(
    city = this.city?.toDomain(),
    items = this.list?.map { it.toDomain() },
)

fun WeatherCityVO.toDomain() = WeatherCityDomain(
    name = this.name,
    coord = this.coord.toDomain(),
    population = this.population,
    sunrise = this.sunrise,
    sunset = this.sunset
)

fun CoordinateVO.toDomain() = CoordinateDomain(
    lat = this.lat,
    lon = this.lon
)

fun WeatherItemVO.toDomain() = WeatherItemDomain(
    date = LocalDateTime.parse(this.dt_txt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
    image = when {
        main.humidity < 60 -> "1"
        main.humidity < 80 -> "2"
        else -> "3"
    },
    infos = this.main.toDomain()
).apply {
    this.day = date.dayOfMonth.toString()
    this.hour = date.hour.toString()
}

fun WeatherInfoVO.toDomain() = WeatherInfoDomain(
    temp = this.temp,
    humidity = this.humidity,
    pressure = this.pressure
)