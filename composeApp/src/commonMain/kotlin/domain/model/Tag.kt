package domain.model

import domain.annotation.TagName


data class Tag(@TagName val name: String, val message: String?)
