package data.remote.responses

import data.model.Current
import data.model.CurrentWeather
import data.model.Daily
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastResponse(
    @SerialName("lat") val lat: Double? = null,
    @SerialName("lon") val lon: Double? = null,
    @SerialName("timezone") val timezone: String? = null,
    @SerialName("timezone_offset") val timezoneOffset: Int? = null,
    @SerialName("current") val current: Current? = null,
    @SerialName("list") val hourly: List<CurrentWeather>? = emptyList(),
    @SerialName("daily") val daily: List<Daily>? = emptyList()
)
