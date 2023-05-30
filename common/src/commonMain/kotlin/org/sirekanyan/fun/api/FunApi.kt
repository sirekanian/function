package org.sirekanyan.`fun`.api

import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.receiveDeserialized
import io.ktor.client.plugins.websocket.sendSerialized
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.http.HttpMethod
import io.ktor.serialization.kotlinx.KotlinxWebsocketSerializationConverter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import org.sirekanyan.`fun`.model.Hello

private const val HOST = "fun.sirekanyan.org"
private const val PORT = 8020

private val client = HttpClient {
    install(WebSockets) {
        contentConverter = KotlinxWebsocketSerializationConverter(Json)
    }
}

class FunApi {

    fun receive(me: String): Flow<Hello> =
        flow {
            client.webSocket(HttpMethod.Get, HOST, PORT, "sync") {
                sendSerialized(me)
                val hello = receiveDeserialized<Hello>()
                emit(hello)
                sendSerialized(Hello(me, hello.from, "I'm fine!"))
                sendSerialized(Unit)
            }
        }

    fun send(me: String, peer: String): Flow<Hello> =
        flow {
            client.webSocket(HttpMethod.Get, HOST, PORT, "sync") {
                sendSerialized(me)
                sendSerialized(Hello(me, peer, "Hello, how are you?"))
                val hello = receiveDeserialized<Hello>()
                emit(hello)
                sendSerialized(Unit)
            }
        }

}