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
