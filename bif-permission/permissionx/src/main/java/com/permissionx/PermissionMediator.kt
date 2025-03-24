package com.permissionx

import android.Manifest
import android.os.Build
import android.os.Environment
import android.provider.Settings
import androidx.fragment.app.FragmentActivity
import com.permissionx.dialog.allSpecialPermissions
import com.permissionx.request.PermissionBuilder
import com.permissionx.request.RequestBackgroundLocationPermission
import com.permissionx.request.RequestBodySensorsBackgroundPermission
import com.permissionx.request.RequestInstallPackagesPermission
import com.permissionx.request.RequestManageExternalStoragePermission
import com.permissionx.request.RequestNotificationPermission
import com.permissionx.request.RequestSystemAlertWindowPermission
import com.permissionx.request.RequestWriteSettingsPermission

/**
 * Mediator class to make PermissionX work easier.
 */
class PermissionMediator(private val activity: FragmentActivity) {

    private val pb = PermissionBuilder(activity)

    /**
     * All permissions that you want to request.
     */
    fun permissions(vararg permissions: String): PermissionBuilder {
        pb.permissions = permissions
        return pb
    }

    /**
     * Request permissions at once to be compatible with older API versions.
     */
    fun permissions(permissions: List<String>): PermissionBuilder {
        pb.permissions = permissions.toTypedArray()
        return pb
    }

    /**
     * Request ACCESS_BACKGROUND_LOCATION permission at once.
     */
    fun requestAccessBackgroundLocationPermission(): PermissionBuilder {
        pb.requestAccessBackgroundLocationPermission = true
        return pb
    }

    /**
     * Request SYSTEM_ALERT_WINDOW permission at once.
     */
    fun requestSystemAlertWindowPermission(): PermissionBuilder {
        pb.requestSystemAlertWindowPermission = true
        return pb
    }

    /**
     * Request WRITE_SETTINGS permission at once.
     */
    fun requestWriteSettingsPermission(): PermissionBuilder {
        pb.requestWriteSettingsPermission = true
        return pb
    }

    /**
     * Request MANAGE_EXTERNAL_STORAGE permission at once.
     */
    fun requestManageExternalStoragePermission(): PermissionBuilder {
        pb.requestManageExternalStoragePermission = true
        return pb
    }

    /**
     * Request REQUEST_INSTALL_PACKAGES permission at once.
     */
    fun requestInstallPackagesPermission(): PermissionBuilder {
        pb.requestInstallPackagesPermission = true
        return pb
    }

    /**
     * Request POST_NOTIFICATIONS permission at once.
     */
    fun requestNotificationPermission(): PermissionBuilder {
        pb.requestNotificationPermission = true
        return pb
    }

    /**
     * Request BODY_SENSORS_BACKGROUND permission at once.
     */
    fun requestBodySensorsBackgroundPermission(): PermissionBuilder {
        pb.requestBodySensorsBackgroundPermission = true
        return pb
    }
}