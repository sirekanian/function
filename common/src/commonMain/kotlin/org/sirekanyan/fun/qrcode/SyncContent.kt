package org.sirekanyan.`fun`.qrcode

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sirekanyan.`fun`.BackHandler
import org.sirekanyan.`fun`.ScanButton
import org.sirekanyan.`fun`.appbar.SmallToolbar
import org.sirekanyan.`fun`.model.AppState
import org.sirekanyan.`fun`.model.MainScreen
import org.sirekanyan.`fun`.systemBarsPadding
import java.util.UUID

@Composable
fun SyncContent(state: AppState) {
    BackHandler {
        state.screen = MainScreen
    }
    val uuid = remember { UUID.randomUUID() }
    val gradient = remember { createQrCodeGradient(uuid) }
    Column(Modifier.fillMaxSize().systemBarsPadding()) {
        SmallToolbar(
            icon = Icons.Default.ArrowBack,
            onIconClick = { state.screen = MainScreen },
            title = "Synchronization",
            action = { ScanButton() },
        )
        Box(Modifier.fillMaxSize().padding(16.dp), Alignment.Center) {
            QrCodeImage("https://sirekanyan.org/fun/$uuid", Modifier.background(gradient))
        }
    }
}
