package com.permissionx.callback;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import com.permissionx.request.ForwardScope;
import com.permissionx.request.PermissionBuilder;

/**
 * Callback for {@link PermissionBuilder#onForwardToSettings(ForwardToSettingsCallback)} method.
 */
public interface ForwardToSettingsCallback {
    /**
     * Called when you should explain to the user why you need these permissions.
     * @param scope The scope for showing explain UI.
     * @param data The data to start settings activity.
     */
    void onForwardToSettings(ForwardScope scope, ForwardScope.ForwardToSettings data);
}