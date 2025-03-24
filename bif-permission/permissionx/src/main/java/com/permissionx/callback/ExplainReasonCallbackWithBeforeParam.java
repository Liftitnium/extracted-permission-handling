package com.permissionx.callback;

import androidx.annotation.NonNull;

import com.permissionx.request.ExplainScope;
import com.permissionx.request.PermissionBuilder;

import java.util.List;

/**
 * Callback for {@link PermissionBuilder#onExplainRequestReason(ExplainReasonCallbackWithBeforeParam)} method.
 */
public interface ExplainReasonCallbackWithBeforeParam {

    /**
     * Called when you should explain to the user why you need these permissions.
     * @param scope The scope for showing explain UI.
     * @param beforeRequest True if this callback is called before requesting permissions, false otherwise.
     */
    void onExplainReason(@NonNull ExplainScope scope, boolean beforeRequest);

}
