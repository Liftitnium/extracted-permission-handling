package com.permissionx.callback;

import androidx.annotation.NonNull;

import com.permissionx.request.ExplainScope;
import com.permissionx.request.PermissionBuilder;

import java.util.List;

/**
 * Callback for explaining permission rationale.
 */
public interface ExplainReasonCallback {

    /**
     * Called when you should explain why you need these permissions.
     * @param scope
     *          Scope to show rationale dialog.
     * @param deniedList
     *          Permissions that you should explain.
     */
    void onExplainReason(@NonNull ExplainScope scope, @NonNull List<String> deniedList);

}
