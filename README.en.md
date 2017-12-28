# Agora Android OpenDuo

*其他语言版本： [简体中文](README.md)*

The Agora Android OpenDuo Sample App is an open-source demo that will help you get video chat integrated directly into your Android applications using the Agora Video SDK and the Agora Signal SDK.

With this sample app, you can:

- Login signal server
- Inquire whether the calling object is online
- Call each other, join the call, end the call
- Mute / unmute audio
- Switch camera

Agora Video SDK supports iOS / Android / Web. You can find demos of these platform here:

- Waiting...

## Running the App
First, create a developer account at [Agora.io](https://dashboard.agora.io/signin/), and obtain an App ID.
Then select the editor in the test project, click App Certificate, and get the App Certificate according to the operation.
Update "app/src/main/res/values/strings.xml" with your App ID and App Certificate.

```
<string name="agora_app_id"><#YOUR APP ID#></string>
<string name="agora_app_certificate"><#YOUR APP Certificate#></string>
```

## Integration mode
- The first step is to download the media SDK and signaling SDK in Agora.io SDK. After decompressing, copy the *.jar under the LIBS folder to the app/libs of this project. The arm64-v8a/x86/armeabi-v7a under the LIBS folder is copied to the app/src/main/libs of this project.

- The second step: add the following code in the Android property of the "app/build.gradle" file in this project (the example is added in this code):

 sourceSets {
        main {
            jniLibs.srcDirs = ['src/main/libs']
        }
    }

- The third step: add the following dependency in the "app/build.gradle" file dependency property of this project (the example is added in this code):

  compile fileTree(dir: 'libs', include: ['*.jar'])


Finally, open project with Android Studio, connect your Android device, build and run.

Or use `Gradle` to build and run.

## Developer Environment Requirements
- Android Studio 2.0 or above
- Real devices (Nexus 5X or other devices)
- Some simulators are function missing or have performance issue, so real device is the best choice

## Connect Us
- You can find full API document at [Document Center](https://docs.agora.io/en/)
- You can file bugs about this demo at [issue](https://github.com/AgoraIO/Agora-Android-Tutorial-1to1/issues)

## License
The MIT License (MIT).
