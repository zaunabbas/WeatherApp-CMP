package domain.repository

import data.DataResource
import data.model.CurrentWeather
import data.remote.responses.ForecastResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getCurrentWeatherByCity(city: String): Flow<DataResource<CurrentWeather>>
    fun getHourlyWeather(lat: Double, lon: Double): Flow<DataResource<ForecastResponse>>

}