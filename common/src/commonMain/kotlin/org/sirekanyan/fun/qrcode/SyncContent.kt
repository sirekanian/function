package org.sirekanyan.`fun`.qrcode

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sirekanyan.`fun`.BackHandler
import org.sirekanyan.`fun`.ScanButton
import org.sirekanyan.`fun`.appbar.SmallToolbar
import org.sirekanyan.`fun`.model.AppState
import org.sirekanyan.`fun`.model.MainScreen
import org.sirekanyan.`fun`.model.SyncScreen
import org.sirekanyan.`fun`.sync.PeerIcon
import org.sirekanyan.`fun`.systemBarsPadding

@Composable
fun SyncContent(state: AppState, screen: SyncScreen) {
    BackHandler {
        state.screen = MainScreen
    }
    Column(Modifier.fillMaxSize().systemBarsPadding()) {
        SmallToolbar(
            icon = Icons.Default.ArrowBack,
            onIconClick = { state.screen = MainScreen },
            title = "Synchronization",
            action = { ScanButton() },
        )
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            QrCodeImage("https://sirekanyan.org/fun/${screen.myUuid}", screen.myUuid)
            AnimatedVisibility(screen.peerUuid != null) {
                screen.peerUuid?.let { peerUuid ->
                    PeerIcon(peerUuid, Modifier.padding(top = 32.dp))
                }
            }
        }
    }
}
