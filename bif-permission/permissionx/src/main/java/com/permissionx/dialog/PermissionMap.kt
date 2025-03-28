package com.permissionx.dialog

import android.Manifest
import android.os.Build
import com.permissionx.PermissionX
import com.permissionx.request.RequestBackgroundLocationPermission
import com.permissionx.request.RequestBodySensorsBackgroundPermission
import com.permissionx.request.RequestInstallPackagesPermission
import com.permissionx.request.RequestManageExternalStoragePermission

/**
 * Contains all the special permissions that we need to handle by special case.
 */
val allSpecialPermissions = setOf(
    RequestBackgroundLocationPermission.ACCESS_BACKGROUND_LOCATION,
    Manifest.permission.SYSTEM_ALERT_WINDOW,
    Manifest.permission.WRITE_SETTINGS,
    RequestManageExternalStoragePermission.MANAGE_EXTERNAL_STORAGE,
    RequestInstallPackagesPermission.REQUEST_INSTALL_PACKAGES,
    PermissionX.permission.POST_NOTIFICATIONS,
    RequestBodySensorsBackgroundPermission.BODY_SENSORS_BACKGROUND
)

/**
 * Based on this link https://developer.android.com/about/versions/10/privacy/changes#permission-groups-removed
 * Since Android Q, we can not get the permission group name by permission name anymore.
 * So we need to keep a track of relationship between permissions and permission groups on every
 * Android release since Android Q.
 */
val permissionMapOnQ = mapOf(
    Manifest.permission.READ_CALENDAR to Manifest.permission_group.CALENDAR,
    Manifest.permission.WRITE_CALENDAR to Manifest.permission_group.CALENDAR,
    Manifest.permission.READ_CALL_LOG to Manifest.permission_group.CALL_LOG,
    Manifest.permission.WRITE_CALL_LOG to Manifest.permission_group.CALL_LOG,
    "android.permission.PROCESS_OUTGOING_CALLS" to Manifest.permission_group.CALL_LOG,
    Manifest.permission.CAMERA to Manifest.permission_group.CAMERA,
    Manifest.permission.READ_CONTACTS to Manifest.permission_group.CONTACTS,
    Manifest.permission.WRITE_CONTACTS to Manifest.permission_group.CONTACTS,
    Manifest.permission.GET_ACCOUNTS to Manifest.permission_group.CONTACTS,
    Manifest.permission.ACCESS_FINE_LOCATION to Manifest.permission_group.LOCATION,
    Manifest.permission.ACCESS_COARSE_LOCATION to Manifest.permission_group.LOCATION,
    Manifest.permission.ACCESS_BACKGROUND_LOCATION to Manifest.permission_group.LOCATION,
    Manifest.permission.RECORD_AUDIO to Manifest.permission_group.MICROPHONE,
    Manifest.permission.READ_PHONE_STATE to Manifest.permission_group.PHONE,
    Manifest.permission.READ_PHONE_NUMBERS to Manifest.permission_group.PHONE,
    Manifest.permission.CALL_PHONE to Manifest.permission_group.PHONE,
    Manifest.permission.ANSWER_PHONE_CALLS to Manifest.permission_group.PHONE,
    Manifest.permission.ADD_VOICEMAIL to Manifest.permission_group.PHONE,
    Manifest.permission.USE_SIP to Manifest.permission_group.PHONE,
    Manifest.permission.ACCEPT_HANDOVER to Manifest.permission_group.PHONE,
    Manifest.permission.BODY_SENSORS to Manifest.permission_group.SENSORS,
    Manifest.permission.ACTIVITY_RECOGNITION to Manifest.permission_group.ACTIVITY_RECOGNITION,
    Manifest.permission.SEND_SMS to Manifest.permission_group.SMS,
    Manifest.permission.RECEIVE_SMS to Manifest.permission_group.SMS,
    Manifest.permission.READ_SMS to Manifest.permission_group.SMS,
    Manifest.permission.RECEIVE_WAP_PUSH to Manifest.permission_group.SMS,
    Manifest.permission.RECEIVE_MMS to Manifest.permission_group.SMS,
    Manifest.permission.READ_EXTERNAL_STORAGE to Manifest.permission_group.STORAGE,
    Manifest.permission.WRITE_EXTERNAL_STORAGE to Manifest.permission_group.STORAGE,
    Manifest.permission.ACCESS_MEDIA_LOCATION to Manifest.permission_group.STORAGE
)

/**
 * Permissions and permission groups mapping on Android R.
 */
val permissionMapOnR = permissionMapOnQ

/**
 * Permissions and permission groups mapping on Android S.
 */
val permissionMapOnS = permissionMapOnR + mapOf(
    Manifest.permission.BLUETOOTH_SCAN to Manifest.permission_group.NEARBY_DEVICES,
    Manifest.permission.BLUETOOTH_ADVERTISE to Manifest.permission_group.NEARBY_DEVICES,
    Manifest.permission.BLUETOOTH_CONNECT to Manifest.permission_group.NEARBY_DEVICES
)

/**
 * Permissions and permission groups mapping on Android T.
 */
val permissionMapOnT = permissionMapOnS + mapOf(
    Manifest.permission.POST_NOTIFICATIONS to Manifest.permission_group.NOTIFICATIONS,
    Manifest.permission.NEARBY_WIFI_DEVICES to Manifest.permission_group.NEARBY_DEVICES,
    Manifest.permission.BODY_SENSORS_BACKGROUND to Manifest.permission_group.SENSORS
)