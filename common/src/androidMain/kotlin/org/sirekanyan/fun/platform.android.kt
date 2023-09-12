package org.sirekanyan.`fun`

import android.Manifest
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.provider.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.LocalContext
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.sirekanyan.`fun`.common.BuildConfig
import org.sirekanyan.`fun`.data.FunDatabase
import org.sirekanyan.`fun`.mlkit.startQrScannerActivity
import androidx.activity.compose.BackHandler as AndroidBackHandler
import androidx.compose.foundation.layout.imePadding as androidImePadding
import androidx.compose.foundation.layout.navigationBarsPadding as androidNavigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding as androidSystemBarsPadding

actual fun ImageBitmap.installMonochromePixels(pixels: BooleanArray) {
    val colors = IntArray(pixels.size) { index ->
        if (pixels[index]) Color.BLACK else Color.TRANSPARENT
    }
    asAndroidBitmap().setPixels(colors, 0, width, 0, 0, width, height)
}

lateinit var androidApplicationContext: Context

actual fun createSqlDriver(): SqlDriver =
    AndroidSqliteDriver(FunDatabase.Schema, androidApplicationContext, "fun.db")

@Composable
actual fun BackHandler(enabled: Boolean, onBack: () -> Unit) {
    AndroidBackHandler(enabled, onBack)
}

@Composable
actual fun ScanButton() {
    var isDialogVisible by remember { mutableStateOf(false) }
    val permissions = rememberPermissions(Manifest.permission.CAMERA) { isGranted ->
        if (isGranted) {
            androidApplicationContext.startQrScannerActivity()
        }
    }
    TextButton(
        onClick = {
            when {
                permissions.isGranted() -> androidApplicationContext.startQrScannerActivity()
                permissions.shouldShowDialog() -> isDialogVisible = true
                else -> permissions.launchPermissionRequest()
            }
        },
    ) {
        Text("Scan")
    }
    if (isDialogVisible) {
        AlertDialog(
            title = { Text("Camera permission") },
            text = { Text("Camera permission is required for scanning QR codes.") },
            onDismissRequest = { isDialogVisible = false },
            confirmButton = {
                if (permissions.shouldShowRationale()) {
                    TextButton(onClick = {
                        isDialogVisible = false
                        permissions.launchPermissionRequest()
                    }) {
                        Text("REQUEST")
                    }
                } else {
                    val context = LocalContext.current
                    TextButton(onClick = {
                        isDialogVisible = false
                        context.startActivity(
                            Intent(
                                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                Uri.fromParts("package", context.packageName, null)
                            )
                        )
                    }) {
                        Text("OPEN SETTINGS")
                    }
                }
            },
        )
    }
}

actual fun Modifier.imePadding(): Modifier =
    androidImePadding()

actual fun Modifier.systemBarsPadding(): Modifier =
    androidSystemBarsPadding()

actual fun Modifier.navigationBarsPadding(): Modifier =
    androidNavigationBarsPadding()

@Suppress("KotlinConstantConditions")
actual fun isFunctionFlavor(): Boolean =
    BuildConfig.FLAVOR == "function"
