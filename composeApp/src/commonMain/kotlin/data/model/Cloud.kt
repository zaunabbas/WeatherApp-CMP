package data.model

import data.base.DataModel
import kotlinx.serialization.Serializable

@Serializable
data class Cloud(
    val all: Int
) : DataModel()
