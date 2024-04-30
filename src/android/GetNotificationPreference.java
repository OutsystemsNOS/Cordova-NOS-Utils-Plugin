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
import android.app.NotificationChannel;
import android.os.Build;

public class GetNotificationPreference extends CordovaPlugin {
  
  @Override
  public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
    try {
      final Activity activity = this.cordova.getActivity();
      
      if (action.equals("getPreference")) {
        NotificationManager notificationManager = (NotificationManager) activity.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (notificationManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = notificationManager.getNotificationChannel("default");
                if (channel.getImportance() >= NotificationManager.IMPORTANCE_DEFAULT) {
                    System.out.println("Notifications are enabled");
                    callbackContext.success("true");
                } else {
                    System.out.println("Notifications are disabled");
                    callbackContext.success("false");
                }
            } else {
                // For versions before Oreo, we can assume notifications are enabled
                System.out.println("Notifications are enabled");
                callbackContext.success("true");
            }
        } else {
            System.out.println("Failed to get NotificationManager");
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
