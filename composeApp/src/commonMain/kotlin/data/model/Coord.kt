package data.model

import data.base.DataModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Coord(
    @SerialName("lat") val lat: Double,
    @SerialName("lon") val long: Double
) : DataModel()
