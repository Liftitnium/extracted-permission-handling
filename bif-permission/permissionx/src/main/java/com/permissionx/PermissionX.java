package com.permissionx;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.permissionx.request.PermissionBuilder;
import com.permissionx.request.PermissionMediator;

/**
 * An Android library that makes handling runtime permissions extremely easy.
 *
 * The following snippet shows the simple usage:
 * <pre>
 *   PermissionX.init(activity)
 *      .permissions(Manifest.permission.READ_CONTACTS, Manifest.permission.CAMERA)
 *      .request { allGranted, grantedList, deniedList ->
 *          // handling the logic
 *      }
 *</pre>
 */
public class PermissionX {

    /**
     * Init PermissionX to make everything prepare to work.
     *
     * @param activity An instance of FragmentActivity
     * @return PermissionCollection instance.
     */
    public static PermissionMediator init(@NonNull FragmentActivity activity) {
        return new PermissionMediator(activity);
    }

    /**
     * Init PermissionX to make everything prepare to work.
     *
     * @param fragment An instance of Fragment
     * @return PermissionCollection instance.
     */
    public static PermissionMediator init(@NonNull Fragment fragment) {
        return new PermissionMediator(fragment);
    }

    /**
     *  A helper function to check a permission is granted or not.
     *
     *  @param context Any context, will not be retained.
     *  @param permission Specific permission name to check. e.g. [android.Manifest.permission.CAMERA].
     *  @return True if this permission is granted, False otherwise.
     */
    public static boolean isGranted(@NonNull Context context, @NonNull String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * A helper function to check are notifications are enabled for current app.
     * @param context
     *          Any context, will not be retained.
     * @return Note that if Android version is lower than N, the return value will always be true.
     */
    public static boolean areNotificationsEnabled(@NonNull Context context) {
        return NotificationManagerCompat.from(context).areNotificationsEnabled();
    }

    public static final class permission {
        /**
         * Define the const to compat with system lower than T.
         */
        public static final String POST_NOTIFICATIONS = "android.permission.POST_NOTIFICATIONS";
    }
}
