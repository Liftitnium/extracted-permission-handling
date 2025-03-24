package com.permissionx.request

import com.permissionx.PermissionX
import com.permissionx.callback.RequestCallback
import com.permissionx.dialog.RationaleDialog
import com.permissionx.dialog.RationaleDialogFragment
import com.permissionx.model.ExplainScope
import com.permissionx.model.ForwardScope
import java.util.*

/**
 * Implementation for request normal permissions.
 */
internal class RequestNormalPermissions(pb: PermissionBuilder) : BaseTask(pb) {

    override fun requestImplementation() {
        val permissions = pb.permissions
        val deniedList = ArrayList<String>()
        val grantedList = ArrayList<String>()
        for (permission in permissions) {
            if (permission == PermissionX.permission.POST_NOTIFICATIONS) {
                if (checkRequestPostNotificationPermission(permission)) {
                    grantedList.add(permission)
                } else {
                    deniedList.add(permission)
                }
            } else if (permission == PermissionX.permission.ACCESS_BACKGROUND_LOCATION) {
                if (checkRequestLocationPermission(permission)) {
                    if (PermissionX.isGranted(pb.activity, permission)) {
                        grantedList.add(permission)
                    } else {
                        deniedList.add(permission)
                    }
                } else {
                    deniedList.add(permission)
                }
            } else if (permission == PermissionX.permission.BODY_SENSORS_BACKGROUND) {
                if (checkRequestBodySensorsBackgroundPermission(permission)) {
                    if (PermissionX.isGranted(pb.activity, permission)) {
                        grantedList.add(permission)
                    } else {
                        deniedList.add(permission)
                    }
                } else {
                    deniedList.add(permission)
                }
            } else {
                if (PermissionX.isGranted(pb.activity, permission)) {
                    grantedList.add(permission)
                } else {
                    deniedList.add(permission)
                }
            }
        }
        if (deniedList.isEmpty()) {
            finish()
            return
        }
        if (pb.explainReasonCallback != null || pb.explainReasonCallbackWithBeforeParam != null) {
            val callback = object : RequestCallback {
                override fun onResult(permissions: Array<String>, allGranted: Boolean) {
                    if (allGranted) {
                        requestAgain(permissions)
                    } else {
                        val scope = ForwardScope(pb, this@RequestNormalPermissions)
                        if (pb.forwardToSettingsCallback != null) {
                            pb.forwardToSettingsCallback?.onForwardToSettings(scope, ForwardScope.ForwardToSettings(permissions))
                        } else if (pb.forwardToSettingsList.isNotEmpty()) {
                            pb.forwardToSettingsList.forEach { callback ->
                                callback.onForwardToSettings(scope, ForwardScope.ForwardToSettings(permissions))
                            }
                        } else {
                            requestAgain(permissions)
                        }
                    }
                }
            }
            if (pb.explainReasonCallback != null) {
                pb.explainReasonCallback?.onExplainReason(ExplainScope(pb, this))
            } else if (pb.explainReasonCallbackWithBeforeParam != null) {
                pb.explainReasonCallbackWithBeforeParam?.onExplainReason(ExplainScope(pb, this), false)
            }
        } else {
            requestAgain(deniedList.toTypedArray())
        }
    }

    private fun finish() {
        pb.grantedPermissions.addAll(pb.permissions)
        pb.finish()
    }

    /**
     * If permission is denied by user and [ExplainScope.showRequestReasonDialog] or [ForwardScope.showForwardToSettingsDialog] is called,
     * when user clicked positive button, will call this method.
     * @param permissions   permissions to request again.
     */
    override fun requestAgain(permissions: List<String>) {
        val permissionsToRequestAgain: MutableSet<String> = HashSet(pb.grantedPermissions)
        permissionsToRequestAgain.addAll(permissions)
        if (permissionsToRequestAgain.isNotEmpty()) {
            pb.requestNow(permissionsToRequestAgain, this)
        } else {
            finish()
        }
    }
}