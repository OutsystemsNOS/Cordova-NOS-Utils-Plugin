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

public class NOSUtilsPlugin extends CordovaPlugin {

  public static final int TAKE_PIC_SEC = 0;
  
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
        callbackContext.error("NOSUtilsPlugin Failed to get action ");
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
                      PermissionHelper.requestPermission(this, TAKE_PIC_SEC, Manifest.permission.CAMERA);
                    }                  
                    callbackContext.success(Boolean.toString(true));
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
    }

  @Override
  public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
      super.onRequestPermissionsResult(requestCode, permissions, grantResults);
      if (requestCode == TAKE_PIC_SEC) {
          if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
              // Permission granted
              callbackContext.success(Boolean.toString(true));
          } else {
              // Permission denied
              callbackContext.success(Boolean.toString(false));
          }
      }
  }
}
