const fs = require('fs');
const path = require('path');

module.exports = function (context) {
    const platformRoot = path.join(context.opts.projectRoot, 'platforms/android');
    const manifestPath = path.join(platformRoot, 'app/src/main/AndroidManifest.xml');

    if (fs.existsSync(manifestPath)) {
        let manifestLines = fs.readFileSync(manifestPath, 'utf8');

        // Check if allowBackup already exists
        if (manifestLines.includes('android:allowBackup=')) {
            // Replace existing value with false
            manifestLines = manifestLines.replace(/android:allowBackup="[^"]*"/, 'android:allowBackup="false"');
        } else {
            // Inject it into the <application> tag
            manifestLines = manifestLines.replace('<application', '<application android:allowBackup="false"');
        }

        fs.writeFileSync(manifestPath, manifestLines, 'utf8');
        console.log('✔ Successfully set android:allowBackup to false in AndroidManifest.xml');
    } else {
        console.error('❌ AndroidManifest.xml not found at: ' + manifestPath);
    }
};
