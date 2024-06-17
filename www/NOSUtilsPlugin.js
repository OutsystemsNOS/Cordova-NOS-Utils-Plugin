var PLUGIN_NAME = "NOSUtilsPlugin";

var exec = require("cordova/exec");

exports.getPreference =
function() {
    return new Promise(function(success, error) {
        exec(success, error, PLUGIN_NAME, "getPreference", []);
    });
};

exports.callCameraPermission =
function() {
    return new Promise(function(success, error) {
        exec(success, error, PLUGIN_NAME, "callCameraPermission", []);
    });
};
