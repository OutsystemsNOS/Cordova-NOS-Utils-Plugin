const fs = require('fs');
const path = require('path');

module.exports = function (context) {
    const platformRoot = path.join(context.opts.projectRoot, 'platforms/android');
    const manifestPath = path.join(platformRoot, 'app/src/main/AndroidManifest.xml');

    const preferences = context.opts.plugin.pluginInfo.getPreferences();
    const allowBackupValue = preferences['ALLOW_ANDROID_BACKUP'] || 'false';

    if (fs.existsSync(manifestPath)) {
        let manifestLines = fs.readFileSync(manifestPath, 'utf8');

        // 1. Add tools namespace to <manifest> tag if it doesn't exist
        if (!manifestLines.includes('xmlns:tools="http://schemas.android.com/tools"')) {
            manifestLines = manifestLines.replace('<manifest', '<manifest xmlns:tools="http://schemas.android.com/tools"');
        }

        // 2. Handle android:allowBackup
        if (manifestLines.includes('android:allowBackup=')) {
            manifestLines = manifestLines.replace(/android:allowBackup="[^"]*"/, `android:allowBackup="${allowBackupValue}"`);
        } else {
            manifestLines = manifestLines.replace('<application', '<application android:allowBackup="' + allowBackupValue + '"');
        }

        // 3. Add tools:replace="android:allowBackup" to override external SDKs like AppsFlyer
        if (manifestLines.includes('tools:replace=')) {
            // If tools:replace already exists, check if it contains allowBackup, otherwise append it
            if (!manifestLines.includes('android:allowBackup')) {
                manifestLines = manifestLines.replace(/tools:replace="([^"]*)"/, 'tools:replace="$1,android:allowBackup"');
            }
        } else {
            manifestLines = manifestLines.replace('<application', '<application tools:replace="android:allowBackup"');
        }

        fs.writeFileSync(manifestPath, manifestLines, 'utf8');
        console.log(`✔ Successfully forced android:allowBackup to ${allowBackupValue} overriding SDK conflicts.`);
    } else {
        console.error('❌ AndroidManifest.xml not found at: ' + manifestPath);
    }
};
