package org.sirekanyan.`fun`.model

import kotlinx.serialization.Serializable

fun createHello(from: String, to: String, items: List<Item>): Hello =
    Hello(from, to, items.map(Item::toDto))

@Serializable
data class Hello(val from: String, val to: String, val dto: List<ItemDto>) {
    fun getItems(): List<Item> = dto.map(ItemDto::fromDto)
}