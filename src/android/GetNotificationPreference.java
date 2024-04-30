import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public class GetNotificationPreference extends CordovaPlugin {
  
  @Override
  public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
    try {
      final Activity activity = this.cordova.getActivity();
      
      if (action.equals("getPreference")) {
        NotificationManager notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);

        if (notificationManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // For Android Oreo and above, check notification channel importance
                NotificationChannel channel = notificationManager.getNotificationChannel(NotificationManager.DEFAULT_CHANNEL_ID);
                if (channel != null && channel.getImportance() >= NotificationManager.IMPORTANCE_DEFAULT) {
                    System.out.println("Notifications are enabled");
                    callbackContext.success("true");
                } else {
                    System.out.println("Notifications are disabled");
                    callbackContext.success("false Notifications are disabled");
                }
            } else {
                // For versions before Oreo, we can assume notifications are enabled
                System.out.println("Notifications are enabled");
                callbackContext.success("true");
            }
        } else {
            System.out.println("Failed to get NotificationManager");
            callbackContext.success("false Failed to get NotificationManager");
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
