#import "NOSUtilsPlugin.h"
#import "UIKit/UIKit.h"

@implementation NOSUtilsPlugin

- (void)getPreference:(CDVInvokedUrlCommand *)command {
    BOOL enabled = NO;
    UIApplication *application = [UIApplication sharedApplication];

    if ([[UIApplication sharedApplication] respondsToSelector:@selector(registerUserNotificationSettings:)]) {
        enabled = application.currentUserNotificationSettings.types != UIUserNotificationTypeNone;
    } else {
        enabled = application.enabledRemoteNotificationTypes != UIRemoteNotificationTypeNone;
    }

    NSString *isEnabledString = enabled ? @"true" : @"false"; // Convert BOOL to lowercase string

    CDVPluginResult *commandResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:isEnabledString];
    [self.commandDelegate sendPluginResult:commandResult callbackId:command.callbackId];    
}

@end
