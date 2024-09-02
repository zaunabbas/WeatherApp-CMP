package data.model

import data.base.DataModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Temp(
    @SerialName("day") val dt: Double,
    @SerialName("min") val min: Double,
    @SerialName("max") val max: Double,
    @SerialName("night") val night: Double,
    @SerialName("eve") val eve: Double,
    @SerialName("morn") val morn: Double
) : DataModel()
