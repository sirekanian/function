package org.sirekanyan.`fun`

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sirekanyan.`fun`.data.FunRepository
import org.sirekanyan.`fun`.qrcode.QrCodeImage
import org.sirekanyan.`fun`.qrcode.createQrCodeGradient
import java.util.*
import kotlin.math.abs
import kotlin.random.Random

@Composable
fun App(colorScheme: ColorScheme) {
    MaterialTheme(colorScheme) {
        AppContent()
    }
}

@Composable
private fun AppContent() {
    val repository = remember { FunRepository() }
    val items by remember { repository.observeItems() }.collectAsState(listOf())
    Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Box(contentAlignment = Alignment.BottomEnd) {
            if (items.isNotEmpty()) {
                MainContent(repository, items)
            } else {
                val uuid = remember { UUID.randomUUID() }
                val gradient = remember { createQrCodeGradient(uuid) }
                Box(Modifier.fillMaxSize().padding(16.dp), Alignment.Center) {
                    QrCodeImage("https://sirekanyan.org/fun/$uuid", Modifier.background(gradient))
                }
            }
            FloatingActionButton(
                onClick = { repository.putContent("$platformName Item ${abs(Random.nextInt()) % 1000}") },
                modifier = Modifier.padding(16.dp),
            ) {
                Icon(Icons.Filled.Add, null)
            }
        }
    }
}
