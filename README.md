# Open Duo for Android

*其他语言版本： [简体中文](README.zh.md)*

The Agora Android OpenDuo Sample App is an open-source demo that will help you get video chat integrated directly into your Android applications using the Agora Video SDK and the Agora Signaling SDK.

With this sample app, you can:

- Login signaling server
- Inquire whether the calling subscriber is online
- Call each other, join the call, end the call
- Mute / unmute audio
- Switch camera

Agora Video SDK supports iOS / Android / Web. You can find demos of these platform here:

- [OpenDuo-iOS-Objective-C](https://github.com/AgoraIO/OpenDuo-iOS-Objective-C)
- [OpenDuo-Web](https://github.com/AgoraIO/OpenDuo-Web)

## Running the App
**First**, create a developer account at [Agora.io](https://dashboard.agora.io/signin/), and obtain an App ID.
Update "app/src/main/res/values/strings.xml" with your App ID .

```
<string name="agora_app_id"><#YOUR APP ID#></string>

```

`Currently this project is compatible with Agora Video SDK 2.1.0 and Agora Signaling SDK 1.2.1`

**Second**, download the video SDK and signaling SDK from [Agora.io SDK](https://www.agora.io/en/download/). Unzip the downloaded SDK package and copy ***.jar** under **libs** to **app/libs**, **arm64-v8a**/**x86**/**armeabi-v7a** under **libs** to **app/src/main/jniLibs**.

Then, add the following code in the property of the dependence of the "app/build.gradle"(current sample is already with it):

```
  compile fileTree(dir: 'libs', include: ['*.jar'])
```

**Finally**, open project with Android Studio, connect your Android device, build and run.

Or use `Gradle` to build and run.

## Developer Environment Requirements
- Android Studio 2.0 or above
- Real devices (Nexus 5X or other devices)
- Some simulators are function missing or have performance issue, so real device is the best choice

## Connect Us
- You can find full API document at [Document Center](https://docs.agora.io/en/)
- You can file bugs about this demo at [issue](https://github.com/OpenDuo-Android/issues)

## License
The MIT License (MIT).
