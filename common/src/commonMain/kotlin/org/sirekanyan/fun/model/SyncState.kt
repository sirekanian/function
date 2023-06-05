package org.sirekanyan.`fun`.model

import java.util.*

sealed class SyncState(val peer: UUID)

class HelloReceived(hello: Hello) : SyncState(hello.getPeer())

class SuccessSync(hello: Hello) : SyncState(hello.getPeer())
