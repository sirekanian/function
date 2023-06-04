package org.sirekanyan.`fun`.model

import kotlinx.serialization.Serializable

fun Item.toDto(): ItemDto = ItemDto(id, timestamp, content)

fun ItemDto.fromDto(): Item = Item(id, timestamp, content)

@Serializable
class ItemDto(val id: String, val timestamp: Long, val content: String)