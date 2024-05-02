import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.app.NotificationManager;
import androidx.core.app.NotificationManagerCompat;
import android.content.Context;
import android.os.Build;

public class GetNotificationPreference extends CordovaPlugin {
  
  @Override
  public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
    try {
      final Activity activity = this.cordova.getActivity();

      if (action.equals("getPreference")) {
        //NotificationManager notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);

        if (NotificationManagerCompat != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // For Android Oreo and above, check notification channel importance
                if (notificationManagerCompat.areNotificationsEnabled()) {
                    System.out.println("GetNotificationPreference Notifications are enabled");
                    callbackContext.success("true");
                } else {
                    System.out.println("GetNotificationPreference Notifications are disabled");
                    callbackContext.success("false");
                }
            } else {
                // For versions before Oreo, we can't determine notification status accurately
                // We can assume notifications are enabled
                System.out.println("GetNotificationPreference Notifications are enabled");
                callbackContext.success("true");
            }
        } else {
            System.out.println("GetNotificationPreference Failed to get NotificationManager");
            callbackContext.success("false");
        }
        
        return true;
      }
      return false;
    } catch (Exception e) {
        callbackContext.error("GetNotificationPreference Failed to get Notifications - " + e.getMessage());
      return true;
    }
  }
}
