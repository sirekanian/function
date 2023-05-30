package org.sirekanyan.`fun`.model

import kotlinx.serialization.Serializable

@Serializable
data class Hello(val from: String, val to: String, val message: String)