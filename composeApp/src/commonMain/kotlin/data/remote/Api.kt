package data.remote

import data.Constants.OpenWeather.API_BASE_URL
import data.Constants.OpenWeather.APP_ID
import data.Constants.OpenWeather.WEATHER_UNITS
import data.model.CurrentWeather
import data.remote.responses.ForecastResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.http.parameters
import presentation.model.CurrentWeatherViewDataModel
import presentation.model.factory.createCurrentWeather
import kotlin.coroutines.cancellation.CancellationException

interface Api {

    suspend fun getCurrentWeatherByCity(
        city: String,
        units: String = WEATHER_UNITS,
        appId: String = APP_ID,
    ): CurrentWeather

    suspend fun getHourlyWeather(
        lat: Double,
        long: Double,
        units: String = WEATHER_UNITS,
        appId: String = APP_ID,
    ): ForecastResponse

    /*@GET("weather")
    suspend fun getCurrentWeatherByCity(
        @Query("q") city: String,
        @Query("units") units: String = WEATHER_UNITS,
        @Query("appid") appId: String = APP_ID,
    ): CurrentWeather

    @GET("forecast")
    suspend fun getHourlyWeather(
        @Query("lat") lat: Double,
        @Query("lon") long: Double,
        @Query("units") units: String = WEATHER_UNITS,
        @Query("appid") appId: String = APP_ID,
    ): ForecastResponse*/
}

class KtorWeatherApi(private val client: HttpClient) : Api {
    companion object {
        private const val API_URL = API_BASE_URL
    }

    override suspend fun getCurrentWeatherByCity(
        city: String,
        units: String,
        appId: String
    ): CurrentWeather {

        return try {
            client.get {
                url(API_URL + "weather")
                parameter("q", city)
                parameter("units", units)
                parameter("appId", appId)
            }.body()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()
            CurrentWeather()
        }

    }

    override suspend fun getHourlyWeather(
        lat: Double,
        long: Double,
        units: String,
        appId: String
    ): ForecastResponse {
        return try {
            client.get {
                url(API_URL + "forecast")
                parameter("lat", lat)
                parameter("lon", long)
                parameter("units", units)
                parameter("appId", appId)
            }.body()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()
            ForecastResponse()
        }
    }

}
