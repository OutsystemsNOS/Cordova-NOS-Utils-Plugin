const fs = require('fs');
const path = require('path');

module.exports = function (context) {
    const platformRoot = path.join(context.opts.projectRoot, 'platforms/android');
    const manifestPath = path.join(platformRoot, 'app/src/main/AndroidManifest.xml');

    // Get preference value from plugin.xml / OutSystems variables
    const preferences = context.opts.plugin.pluginInfo.getPreferences();
    const allowBackupValue = preferences['ALLOW_ANDROID_BACKUP'] || 'false';

    if (fs.existsSync(manifestPath)) {
        let manifestLines = fs.readFileSync(manifestPath, 'utf8');

        // Check if allowBackup already exists in the manifest
        if (manifestLines.includes('android:allowBackup=')) {
            // Replace existing value with the preference value
            const regex = /android:allowBackup="[^"]*"/;
            manifestLines = manifestLines.replace(regex, `android:allowBackup="${allowBackupValue}"`);
        } else {
            // Inject it into the <application> tag
            manifestLines = manifestLines.replace('<application', `<application android:allowBackup="${allowBackupValue}"`);
        }

        fs.writeFileSync(manifestPath, manifestLines, 'utf8');
        console.log(`✔ Successfully set android:allowBackup to ${allowBackupValue} in AndroidManifest.xml`);
    } else {
        console.error('❌ AndroidManifest.xml not found at: ' + manifestPath);
    }
};
