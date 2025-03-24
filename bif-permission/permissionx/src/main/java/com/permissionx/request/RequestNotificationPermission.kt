package com.permissionx.request

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import com.permissionx.PermissionX

/**
 * Request for notification permission.
 */
internal class RequestNotificationPermission internal constructor(permissionBuilder: PermissionBuilder)
    : BaseTask(permissionBuilder) {

    override fun request() {
        if (pb.shouldRequestNotificationPermission()) {
            if (PermissionX.areNotificationsEnabled(pb.activity)) {
                // notification permission has already granted, we can finish this task now.
                finish()
                return
            }
            if (pb.explainReasonCallback != null || pb.explainReasonCallbackWithBeforeParam != null) {
                val requestList = mutableListOf(PermissionX.permission.POST_NOTIFICATIONS)
                if (pb.explainReasonCallbackWithBeforeParam != null) {
                    // callback ExplainReasonCallbackWithBeforeParam prior to ExplainReasonCallback
                    pb.explainReasonCallbackWithBeforeParam!!.onExplainReason(explainScope, requestList, true)
                } else {
                    pb.explainReasonCallback!!.onExplainReason(explainScope, requestList)
                }
                return
            }
        }
        // Shouldn't request notification at this time, so we call finish() to finish this task.
        finish()
    }

    override fun requestAgain(permissions: List<String>) {
        // Don't care what the permissions param is, always request notification permission.
        pb.requestNotificationPermissionNow(this)
    }
}