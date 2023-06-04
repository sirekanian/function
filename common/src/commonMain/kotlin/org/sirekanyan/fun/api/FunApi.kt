package org.sirekanyan.`fun`.api

import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import org.sirekanyan.`fun`.data.FunRepository
import org.sirekanyan.`fun`.model.Hello
import org.sirekanyan.`fun`.model.createHello

private const val HOST = "fun.sirekanyan.org"
private const val PORT = 8020

private val client = HttpClient {
    install(WebSockets) {
        contentConverter = KotlinxWebsocketSerializationConverter(Json)
    }
}

class FunApi(private val repository: FunRepository) {

    fun receive(me: String): Flow<Hello> =
        flow {
            client.webSocket(HttpMethod.Get, HOST, PORT, "sync") {
                sendSerialized(me)
                val hello = receiveHello()
                emit(hello)
                sendHello(me, hello.from)
                sendSerialized(Unit)
            }
        }

    fun send(me: String, peer: String): Flow<Hello> =
        flow {
            client.webSocket(HttpMethod.Get, HOST, PORT, "sync") {
                sendSerialized(me)
                sendHello(me, peer)
                val hello = receiveHello()
                emit(hello)
                sendSerialized(Unit)
            }
        }

    private suspend fun DefaultClientWebSocketSession.receiveHello(): Hello {
        val hello = receiveDeserialized<Hello>()
        hello.getItems().forEach(repository::putItem)
        return hello
    }

    private suspend fun DefaultClientWebSocketSession.sendHello(me: String, peer: String) {
        sendSerialized(createHello(me, peer, repository.getAllItems()))
    }

}