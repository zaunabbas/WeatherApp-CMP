package data.model

import data.base.DataModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Main(
    @SerialName("temp") val temp: Double,
    @SerialName("feels_like") val feelsLike: Double,
    @SerialName("temp_min") val tempMin: Double,
    @SerialName("temp_max") val tempMax: Double,
    @SerialName("pressure") val pressure: Double,
    @SerialName("humidity") val humidity: Int,
    @SerialName("sea_level") val seaLevel: Double,
    @SerialName("grnd_level") val grndLevel: Double
) : DataModel()
