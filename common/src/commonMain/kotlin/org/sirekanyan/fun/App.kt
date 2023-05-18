package org.sirekanyan.`fun`

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sirekanyan.`fun`.qrcode.QrCodeImage

@Composable
fun App() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(Modifier.padding(16.dp), Arrangement.Center, Alignment.CenterHorizontally) {
            Text("Hello, $platformName")
            Spacer(Modifier.size(16.dp))
            QrCodeImage("https://sirekanyan.org")
        }
    }
}
