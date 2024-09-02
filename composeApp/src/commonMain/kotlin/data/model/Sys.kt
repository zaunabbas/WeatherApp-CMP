package data.model

import data.base.DataModel
import kotlinx.serialization.SerialName

import kotlinx.serialization.Serializable

@Serializable
data class Sys(
    @SerialName("type") val type: Int? = null,
    @SerialName("id") val id: Int? = null,
    @SerialName("country") val country: String? = null,
    @SerialName("sunrise") val sunrise: Long? = null,
    @SerialName("sunset") val sunset: Long? = null
) : DataModel()
