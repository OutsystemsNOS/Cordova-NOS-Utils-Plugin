


## Cordova-Get-Notification-Preference

This plugin can be used to get user notifications preference on the App

### Methods

#### getPreference

```bash
window.GetNotificationPreference.getPreference().then(success).catch(error);

function success(obj) {   
    console.log(obj); 
    $resolve();
}

function error(error) {    
    console.log(error);
    $resolve();
}
```

#### callCameraPermission (Android ONLY)

```bash
window.NOSUtilsPlugin.callCameraPermission().then(success).catch(error);

function success(obj) {   
    $parameters.IsSuccess = true;
    $parameters.Result = obj; 
    $resolve();
}

function error(error) {    
    $parameters.IsSuccess = false;
    $parameters.ErrorMessage = error;
    $resolve();
}
```

#### hasCameraPermission (Android ONLY)

```bash
window.NOSUtilsPlugin.hasCameraPermission().then(success).catch(error);

function success(obj) {   
    $parameters.IsSuccess = true;
    $parameters.Result = obj; 
    $resolve();
}

function error(error) {    
    $parameters.IsSuccess = false;
    $parameters.ErrorMessage = error;
    $resolve();
}
```
