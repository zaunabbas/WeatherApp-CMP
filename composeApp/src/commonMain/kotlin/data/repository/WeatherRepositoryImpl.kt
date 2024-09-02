package data.repository

import data.DataResource
import data.callApi
import data.remote.Api
import domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class WeatherRepositoryImpl(
    private val apiService: Api
) : WeatherRepository {

    override fun getCurrentWeatherByCity(city: String) = flow {
        emit(DataResource.Loading)
        val result = callApi {
            apiService.getCurrentWeatherByCity(city)
        }
        emit(result)
    }.flowOn(Dispatchers.IO)

    override fun getHourlyWeather(lat: Double, lon: Double) = flow {
        emit(DataResource.Loading)
        val result = callApi {
            apiService.getHourlyWeather(
                lat = lat,
                long = lon
            )
        }
        emit(result)
    }.flowOn(Dispatchers.IO)

}