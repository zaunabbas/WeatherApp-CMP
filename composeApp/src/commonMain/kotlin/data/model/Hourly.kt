package data.model

import data.base.DataModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Hourly(
    @SerialName("dt") val dt: Long,
    @SerialName("temp") val temp: Double,
    @SerialName("feels_like") val feelsLike: Double,
    @SerialName("pressure") val pressure: Double,
    @SerialName("humidity") val humidity: Int,
    @SerialName("dew_point") val dewPoint: Double,
    @SerialName("uvi") val uvi: Double,
    @SerialName("clouds") val clouds: Double,
    @SerialName("visibility") val visibility: Int,
    @SerialName("wind_speed") val windSpeed: Double,
    @SerialName("wind_deg") val windDeg: Int,
    @SerialName("wind_gust") val windGust: Double,
    @SerialName("weather") val weather: List<WeatherItem>,
    @SerialName("pop") val pop: Double
) : DataModel()
