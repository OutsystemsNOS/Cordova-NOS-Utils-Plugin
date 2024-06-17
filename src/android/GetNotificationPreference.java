package com.cordova.plugin;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.CallbackContext;
import android.Manifest;

import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.core.app.NotificationManagerCompat;
import android.util.Base64;
import android.util.Log;

import org.apache.cordova.PermissionHelper;
import android.content.pm.PackageManager;

public class GetNotificationPreference extends CordovaPlugin {
  
  @Override
  public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
    try {
      if (action.equals("getPreference")) {
        this.getPreference(callbackContext);        
        return true;
      } else if (action.equals("callCameraPermission")){
        this.callCameraPermission(callbackContext);        
        return true;
      }
      return false;
    } catch (Exception e) {
        callbackContext.error("GetNotificationPreference Failed to get Notifications - " + e.getMessage());
      return true;
    }
  }
  
  private void getPreference(final CallbackContext callbackContext) {
                try {
                    Context context = cordova.getActivity();
                    NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
                    boolean areNotificationsEnabled = notificationManagerCompat.areNotificationsEnabled();
                    callbackContext.success(Boolean.toString(areNotificationsEnabled));
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
    }

    private void callCameraPermission(final CallbackContext callbackContext) {
                try {
                    boolean takePicturePermission = PermissionHelper.hasPermission(this, Manifest.permission.CAMERA);
                    if (!takePicturePermission) {
                    takePicturePermission = true;

                        PackageManager packageManager = this.cordova.getActivity().getPackageManager();
                        String[] permissionsInPackage = packageManager.getPackageInfo(this.cordova.getActivity().getPackageName(), PackageManager.GET_PERMISSIONS).requestedPermissions;
                        if (permissionsInPackage != null) {
                            for (String permission : permissionsInPackage) {
                                if (permission.equals(Manifest.permission.CAMERA)) {
                                    takePicturePermission = false;
                                    break;
                                }
                            }
                        }
                }
                  
                callbackContext.success(Boolean.toString(true));
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
    }
}
