


## Cordova-Get-Notification-Preference

This plugin can be used to get user notifications preference on the App

### Methods

#### getPreference

```bash
window.GetNotificationPreference.getPreference().then(success).catch(error);

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


    
