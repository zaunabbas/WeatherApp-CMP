package data.model

import data.base.DataModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeelLike(
    @SerialName("day") val day: Double,
    @SerialName("night") val night: Double,
    @SerialName("eve") val eve: Double,
    @SerialName("morn") val morn: Double
) : DataModel()
