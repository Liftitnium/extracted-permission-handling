package com.permissionx.callback;

import androidx.annotation.NonNull;

import com.permissionx.request.PermissionBuilder;

import java.util.List;

/**
 * Callback for {@link PermissionBuilder#request(RequestCallback)} method.
 */
public interface RequestCallback {

    /**
     * Called when the request process is finished.
     * @param permissions All permissions that you requested, no matter they are granted or not.
     * @param allGranted True if all permissions are granted, false otherwise.
     */
    void onResult(String[] permissions, boolean allGranted);

}
