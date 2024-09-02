package data.model

import data.base.DataModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Current(
    @SerialName("dt") val dt: Long,
    @SerialName("sunrise") val sunrise: Long,
    @SerialName("sunset") val sunset: Long,
    @SerialName("temp") val temp: Double,
    @SerialName("feels_like") val feelsLike: Double,
    @SerialName("pressure") val pressure: Int,
    @SerialName("humidity") val humidity: Int,
    @SerialName("dew_point") val dewPoint: Double,
    @SerialName("wind_speed") val windSpeed: Double,
    @SerialName("wind_deg") val windDeg: Double,
    @SerialName("wind_gust") val windGust: Double,
    @SerialName("weather") val weather: List<WeatherItem>,
    @SerialName("clouds") val clouds: Int,
    @SerialName("pop") val pop: Int,
    @SerialName("visibility") val visibility: Int,
    @SerialName("uvi") val uvi: Double
) : DataModel()
