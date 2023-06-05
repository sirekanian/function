package org.sirekanyan.`fun`.qrcode

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.sirekanyan.`fun`.BackHandler
import org.sirekanyan.`fun`.ScanButton
import org.sirekanyan.`fun`.api.FunApi
import org.sirekanyan.`fun`.appbar.SmallToolbar
import org.sirekanyan.`fun`.model.*
import org.sirekanyan.`fun`.sync.PeerIcon
import org.sirekanyan.`fun`.systemBarsPadding

@Composable
fun SyncContent(state: AppState, screen: SyncScreen, api: FunApi) {
    val scannedPeerId = screen.peerUuid
    val syncState: SyncState? by remember {
        if (scannedPeerId == null) {
            api.receive(screen.myUuid.toString())
        } else {
            api.send(screen.myUuid.toString(), scannedPeerId.toString())
        }
    }.collectAsState(initial = null)
    val peerUuid = remember(syncState) { scannedPeerId ?: syncState?.peer }
    val isSuccess = remember(syncState) { syncState is SuccessSync }
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
            AnimatedVisibility(peerUuid != null && !isSuccess) {
                peerUuid?.let {
                    PeerIcon(it, Modifier.padding(top = 32.dp))
                }
            }
            AnimatedVisibility(isSuccess) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Done",
                    modifier = Modifier.padding(top = 32.dp)
                        .clip(RoundedCornerShape(32.dp))
                        .size(64.dp)
                        .background(MaterialTheme.colorScheme.tertiary)
                        .padding(8.dp),
                    tint = MaterialTheme.colorScheme.onTertiary,
                )
            }
        }
    }
    LaunchedEffect(isSuccess) {
        if (isSuccess) {
            delay(1000)
            state.screen = HomeScreen
        }
    }
}
