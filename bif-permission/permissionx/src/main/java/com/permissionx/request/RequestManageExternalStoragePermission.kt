package com.permissionx.request

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings

/**
 * Request for manage external storage permission.
 */
class RequestManageExternalStoragePermission : BaseTask {

    override fun request() {
        if (pb.shouldRequestManageExternalStoragePermission() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (Environment.isExternalStorageManager()) {
                // MANAGE_EXTERNAL_STORAGE permission has already granted, we can finish this task now.
                finish()
                return
            }
            if (pb.explainReasonCallback != null || pb.explainReasonCallbackWithBeforeParam != null) {
                val requestList = mutableListOf(MANAGE_EXTERNAL_STORAGE)
                if (pb.explainReasonCallbackWithBeforeParam != null) {
                    // callback ExplainReasonCallbackWithBeforeParam prior to ExplainReasonCallback
                    pb.explainReasonCallbackWithBeforeParam!!.onExplainReason(explainScope, requestList, true)
                } else {
                    pb.explainReasonCallback!!.onExplainReason(explainScope, requestList)
                }
            } else {
                // No implementation of explainReasonCallback, we can't request
                // MANAGE_EXTERNAL_STORAGE permission at this time, because user won't understand why.
                finish()
            }
            return
        }
        // shouldn't request MANAGE_EXTERNAL_STORAGE permission at this time, so we call finish()
        // to finish this task.
        finish()
    }

    override fun requestAgain(permissions: List<String>) {
        // don't care what the permissions param is, always request WRITE_SETTINGS permission.
        pb.requestManageExternalStoragePermissionNow(this)
    }

    companion object {
        /**
         * Define the const to compat with system lower than R.
         */
        const val MANAGE_EXTERNAL_STORAGE = "android.permission.MANAGE_EXTERNAL_STORAGE"
    }
}