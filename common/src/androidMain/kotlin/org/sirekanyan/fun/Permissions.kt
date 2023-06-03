@file:OptIn(ExperimentalPermissionsApi::class)

package org.sirekanyan.`fun`

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@Composable
fun rememberPermissions(permission: String, onPermissionResult: (Boolean) -> Unit): Permissions {
    val context = LocalContext.current
    val permissionState = rememberPermissionState(permission, onPermissionResult)
    val preferences = remember { context.getSharedPreferences("permissions", Context.MODE_PRIVATE) }
    return remember { PermissionsImpl(permissionState, preferences) }
}

interface Permissions {

    fun isGranted(): Boolean

    fun shouldShowDialog(): Boolean

    fun shouldShowRationale(): Boolean

    fun launchPermissionRequest()

}

class PermissionsImpl(
    private val permissionState: PermissionState,
    private val preferences: SharedPreferences,
) : Permissions {

    override fun isGranted(): Boolean =
        permissionState.status.isGranted

    override fun shouldShowDialog(): Boolean =
        permissionState.status.shouldShowRationale || isRationaleShown()

    private fun isRationaleShown(): Boolean =
        preferences.getBoolean(permissionState.permission, false)

    override fun shouldShowRationale(): Boolean =
        permissionState.status.shouldShowRationale.also { if (it) setRationaleShown() }

    private fun setRationaleShown(): Unit =
        preferences.edit().putBoolean(permissionState.permission, true).apply()

    override fun launchPermissionRequest(): Unit =
        permissionState.launchPermissionRequest()

}