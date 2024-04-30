var PLUGIN_NAME = "GetNotificationPreference";

var exec = require("cordova/exec");

exports.getPreference =

function() {
    return new Promise(function(resolve, reject) {
        exec(resolve, reject, PLUGIN_NAME, "getPreference", []);
    });
};
