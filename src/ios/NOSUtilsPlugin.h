#import <Cordova/CDV.h>

@interface NOSUtilsPlugin : CDVPlugin

- (void)getPreference:(CDVInvokedUrlCommand*)command;
- (void)callCameraPermission:(CDVInvokedUrlCommand*)command;
- (void)callCameraPermission:(CDVInvokedUrlCommand*)command;

@end
