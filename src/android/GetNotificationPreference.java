import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.core.app.NotificationManagerCompat;
import android.util.Base64;
import android.util.Log;

public class GetNotificationPreference extends CordovaPlugin {
  
  @Override
  public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
    try {
      if (action.equals("getPreference")) {
        Log.d("GetNotificationPreference", "getPreference");
        this.getPreference(callbackContext);        
        return true;
      }
      return false;
    } catch (Exception e) {
        callbackContext.error("GetNotificationPreference Failed to get Notifications - " + e.getMessage());
      return true;
    }
  }
  
  private void getPreference(final CallbackContext callbackContext) {
        cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                try {
                    Log.d("GetNotificationPreference", "getPreference run");
                    Context context = cordova.getActivity();
                    NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
                    boolean areNotificationsEnabled = notificationManagerCompat.areNotificationsEnabled();
                    Log.d("GetNotificationPreference", "getPreference 1");
                    JSONObject object = new JSONObject();
                    object.put("isEnabled", areNotificationsEnabled);
                    Log.d("GetNotificationPreference", "getPreference 2");
                    callbackContext.success(object);
                } catch (Exception e) {
                    Log.d("GetNotificationPreference", "getPreference error");
                    callbackContext.error(e.getMessage());
                }
            }
        });
    }
}
