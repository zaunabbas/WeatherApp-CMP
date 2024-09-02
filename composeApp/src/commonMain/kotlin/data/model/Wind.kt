package data.model

import data.base.DataModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Wind(
    @SerialName("speed") val speed: Double,
    @SerialName("deg") val deg: Double,
    @SerialName("gust") val gust: Double? = null
) : DataModel()
