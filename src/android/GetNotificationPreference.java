import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.pm.ApplicationInfo;

import android.app.NotificationManager;
import android.content.Context;
import android.app.NotificationManager.Policy;

public class GetNotificationPreference extends CordovaPlugin {
  @Override
  public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
    try {
      if (action.equals("getPreference")) {
        Context context = getApplicationContext();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Get the notification policy
        Policy notificationPolicy = notificationManager.getCurrentNotificationPolicy();

        // Check notification settings
        if (notificationPolicy.areNotificationsEnabled()) {
            callbackContext.success("true");
        } else {
            callbackContext.success("false");
        }
        
        return true;
      }
      return false;
    } catch (Exception e) {
        callbackContext.error("Failed to get Notifications - " + e.getMessage());
      return true;
    }
  }
}
