


## Cordova-Get-Notification-Preference

Cordova plugin to host several native methods, like popup permissions or other technical needs

### Methods

#### getPreference - Get notifications preference of the App

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

#### callCameraPermission - Call camera popup permissions(Android ONLY)

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

#### hasCameraPermission - Check if camera permission is enable or disable (Android ONLY)

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
