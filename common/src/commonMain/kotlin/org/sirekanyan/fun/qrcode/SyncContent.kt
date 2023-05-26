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
import org.sirekanyan.`fun`.appbar.SmallToolbar
import org.sirekanyan.`fun`.model.AppState
import org.sirekanyan.`fun`.model.MainScreen
import java.util.UUID

@Composable
fun SyncContent(state: AppState) {
    val uuid = remember { UUID.randomUUID() }
    val gradient = remember { createQrCodeGradient(uuid) }
    Column(Modifier.fillMaxSize()) {
        SmallToolbar(Icons.Default.ArrowBack, { state.screen = MainScreen }, "Synchronization")
        Box(Modifier.fillMaxSize().padding(16.dp), Alignment.Center) {
            QrCodeImage("https://sirekanyan.org/fun/$uuid", Modifier.background(gradient))
        }
    }
}
