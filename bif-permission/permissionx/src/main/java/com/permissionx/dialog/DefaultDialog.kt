package com.permissionx.dialog

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.permissionx.callback.RequestCallback
import com.permissionx.R
import com.permissionx.databinding.PermissionxDefaultDialogLayoutBinding
import com.permissionx.databinding.PermissionxPermissionItemBinding

/**
 * Default rationale dialog to show if developers did not implement their own custom rationale dialog.
 */
class DefaultDialog(
    context: Context,
    message: String,
    positiveText: String,
    negativeText: String,
    private val callback: RequestCallback?
) : RationaleDialog(context) {

    private val positiveButton: View
    private val negativeButton: View
    private val messageText: TextView

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.permissionx_default_dialog_layout, null)
        positiveButton = view.findViewById(R.id.positiveBtn)
        negativeButton = view.findViewById(R.id.negativeBtn)
        messageText = view.findViewById(R.id.messageText)
        messageText.text = message
        positiveButton.setOnClickListener {
            callback?.onResult(arrayOf(), true)
            dismiss()
        }
        negativeButton.setOnClickListener {
            callback?.onResult(arrayOf(), false)
            dismiss()
        }
        setContentView(view)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        callback?.onResult(arrayOf(), false)
    }

    /**
     * Provide the positive button instance to continue requesting.
     * @return Positive button instance to continue requesting.
     */
    override fun getPositiveButton(): View {
        return positiveButton
    }

    /**
     * Provide the negative button instance to abort requesting.
     * This is alternative. If negativeText is null we just return null, means all these permissions are necessary.
     * @return Negative button instance to abort requesting. Or null if all these permissions are necessary.
     */
    override fun getNegativeButton(): View? {
        return negativeButton
    }

    /**
     * Provide the permissions to request again.
     * @return Permissions to request again.
     */
    override fun getPermissionsToRequest(): List<String> {
        return emptyList()
    }

    /**
     * Check if the permission layout if empty.
     * It is possible if all the permissions passed in are invalid permission such as a string named
     * "hello world". We won't add these into permission layout.
     */
    internal fun isPermissionLayoutEmpty(): Boolean {
        return false
    }

    /**
     * Setup text and text color on the dialog.
     */
    private fun setupText() {
        messageText.text = message
        positiveButton.text = positiveText
        if (negativeText != null) {
            negativeButton.visibility = View.VISIBLE
            negativeButton.text = negativeText
        } else {
            negativeButton.visibility = View.GONE
        }
    }

    /**
     * Add every permission that need to explain the request reason to the dialog.
     * But we only need to add the permission group. So if there're two permissions belong to one group, only one item will be added to the dialog.
     */
    private fun buildPermissionsLayout() {
        // Implementation needed
    }

    /**
     * Setup dialog window to show. Control the different window size in portrait and landscape mode.
     */
    private fun setupWindow() {
        val width = context.resources.displayMetrics.widthPixels
        val height = context.resources.displayMetrics.heightPixels
        if (width < height) {
            // now we are in portrait
            window?.let {
                val param = it.attributes
                it.setGravity(Gravity.CENTER)
                param.width = (width * 0.86).toInt()
                it.attributes = param
            }
        } else {
            // now we are in landscape
            window?.let {
                val param = it.attributes
                it.setGravity(Gravity.CENTER)
                param.width = (width * 0.6).toInt()
                it.attributes = param
            }
        }
    }

    /**
     * Currently we are in dark theme or not.
     */
    private fun isDarkTheme(): Boolean {
        val flag = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return flag == Configuration.UI_MODE_NIGHT_YES
    }
}