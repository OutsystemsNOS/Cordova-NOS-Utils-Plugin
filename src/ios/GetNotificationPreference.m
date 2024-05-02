#import "GetNotificationPreference.h"
#import "UIKit/UIKit.h"

@implementation GetNotificationPreference

- (void)getPreference:(CDVInvokedUrlCommand *)command {
    CDVPluginResult* pluginResult;

    // Get the current notification settings
    UIUserNotificationSettings *notificationSettings = [[UIApplication sharedApplication] currentUserNotificationSettings];
    
    // Check if the app has permission to display notifications
    if (notificationSettings.types != UIUserNotificationTypeNone) {
        NSLog(@"Notifications are enabled");
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    } else {
        NSLog(@"Notifications are disabled");
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"Notifications are disabled"];
    }
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end
