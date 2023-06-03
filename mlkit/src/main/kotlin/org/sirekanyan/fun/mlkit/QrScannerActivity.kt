package org.sirekanyan.`fun`.mlkit

import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.ImageAnalysis.Analyzer
import androidx.camera.mlkit.vision.MlKitAnalyzer
import androidx.camera.view.CameraController
import androidx.camera.view.CameraController.COORDINATE_SYSTEM_VIEW_REFERENCED
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode.FORMAT_QR_CODE
import java.util.concurrent.Executors

fun Context.startQrScannerActivity() {
    startActivity(Intent(this, QrScannerActivity::class.java).setFlags(FLAG_ACTIVITY_NEW_TASK))
}

class QrScannerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val previewView = PreviewView(this)
        setContentView(previewView)
        previewView.controller = createCameraController { barcode ->
            finish()
            startActivity(Intent(ACTION_VIEW, Uri.parse(barcode)))
        }
    }

    private fun createCameraController(onResult: (String) -> Unit): CameraController {
        val cameraController = LifecycleCameraController(baseContext)
        cameraController.setImageAnalysisAnalyzer(
            Executors.newSingleThreadExecutor(),
            createImageAnalyzer(onResult)
        )
        cameraController.bindToLifecycle(this)
        return cameraController
    }

    private fun createImageAnalyzer(onResult: (String) -> Unit): Analyzer {
        val options = BarcodeScannerOptions.Builder().setBarcodeFormats(FORMAT_QR_CODE).build()
        val barcodeScanner = BarcodeScanning.getClient(options)
        return MlKitAnalyzer(
            listOf(barcodeScanner),
            COORDINATE_SYSTEM_VIEW_REFERENCED,
            ContextCompat.getMainExecutor(this),
        ) { analyzeResult ->
            analyzeResult?.getValue(barcodeScanner)?.firstNotNullOfOrNull { it.rawValue }?.let {
                barcodeScanner.close()
                onResult(it)
            }
        }
    }

}