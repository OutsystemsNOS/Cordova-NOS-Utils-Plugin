


## Cordova-Get-Notification-Preference

Cordova plugin to host several native methods, like popup permissions or other technical needs

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
