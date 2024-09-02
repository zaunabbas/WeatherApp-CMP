package data.model

import data.base.DataModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeather(
    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("cod") val cod: Int? = null,
    @SerialName("coord") val coord: Coord? = null,
    @SerialName("weather") val weatherItems: List<WeatherItem>? = null,
    @SerialName("base") val base: String? = null,
    @SerialName("main") val main: Main? = null,
    @SerialName("visibility") val visibility: Int? = null,
    @SerialName("wind") val wind: Wind? = null,
    @SerialName("clouds") val clouds: Cloud? = null,
    @SerialName("dt") val dt: Long? = null,
    @SerialName("sys") val sys: Sys? = null,
    @SerialName("timezone") val timezone: Int? = null
) : DataModel()


