package org.sirekanyan.`fun`.qrcode

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sirekanyan.`fun`.BackHandler
import org.sirekanyan.`fun`.ScanButton
import org.sirekanyan.`fun`.api.FunApi
import org.sirekanyan.`fun`.appbar.SmallToolbar
import org.sirekanyan.`fun`.model.AppState
import org.sirekanyan.`fun`.model.Hello
import org.sirekanyan.`fun`.model.HomeScreen
import org.sirekanyan.`fun`.model.SyncScreen
import org.sirekanyan.`fun`.sync.PeerIcon
import org.sirekanyan.`fun`.systemBarsPadding
import java.util.UUID

@Composable
fun SyncContent(state: AppState, screen: SyncScreen) {
    val api = remember { FunApi() }
    val scannedPeerId = screen.peerUuid
    val hello: Hello? by remember {
        if (scannedPeerId == null) {
            api.receive(screen.myUuid.toString())
        } else {
            api.send(screen.myUuid.toString(), scannedPeerId.toString())
        }
    }.collectAsState(initial = null)
    val peerUuid = remember(hello) { scannedPeerId ?: hello?.from?.let { UUID.fromString(it) } }
    BackHandler {
        state.screen = HomeScreen
    }
    Column(Modifier.fillMaxSize().systemBarsPadding()) {
        SmallToolbar(
            icon = Icons.Default.ArrowBack,
            onIconClick = { state.screen = HomeScreen },
            title = "Synchronization",
            action = { ScanButton() },
        )
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            QrCodeImage("https://sirekanyan.org/fun/${screen.myUuid}", screen.myUuid)
            AnimatedVisibility(peerUuid != null) {
                peerUuid?.let {
                    PeerIcon(it, Modifier.padding(top = 32.dp))
                }
            }
        }
    }
}
