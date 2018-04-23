# Open Duo for Android

*其他语言版本： [简体中文](README.zh.md)*

The Agora OpenDuo Sample App supports the following platforms:
* [iOS](https://github.com/AgoraIO/OpenDuo-iOS-Objective-C)
* [Android](https://github.com/AgoraIO/OpenDuo-Android)
* [Web](https://github.com/AgoraIO/OpenDuo-Web)

This readme describes steps and several considerations for demonstrating the Agora OpenDuo iOS Sample App.

## A Brief Introduction

Built upon the Agora Video SDK and the Agora Signaling SDK, the Agora OpenDuo for iOS is an open-source demo that integrates video chat into your Web applications.

This sample App allows you to:

- Login the signaling server
- Inquire whether the calling subscriber is online
- Call each other
- Accept or hang up a call
- Mute/unmute a user
- Switch camera

## Developer Environment Requirements

- Android Studio 2.0 or later
- Real devices, for example Nexus 5X

NOTE: Agora recommends using a real device instead of a simulator, because some simulators are inadequate and hence may cause issues. 

## Running the App
1. Create a developer account at [Agora.io](https://dashboard.agora.io/signin/), and obtain an App ID. 
2. Fill in your App ID in the *app/src/main/res/values/strings.xml*.

 
        <string name="agora_app_id"><#YOUR APP ID#></string>

3. Download the Agora video SDK and the Agora signaling SDK from [Agora.io SDK](https://www.agora.io/en/download/). 

NOTE: This project is compatible with **Agora Video SDK 2.1.0** and **Agora Signaling SDK 1.2.1**.

4. Unzip the downloaded SDK package and: 

  - copy ***.jar** under **libs** to **app/libs**
  - copy **arm64-v8a**/**x86**/**armeabi-v7a** under **libs** to **app/src/main/jniLibs**.

5. Check if the following code snippet is in the property of the dependence of the "app/build.gradle" (the current sample already has it):

          compile fileTree(dir: 'libs', include: ['*.jar'])

6. Open the project with Android Studio, connect your Android device, and build and run the sample App.

NOTE: You can also use `Gradle` to build and run.

## Contact Us

- You can find the full API document at the [Developer Center](https://docs.agora.io/en/)
- You can file a ticket about this demo at [issue](https://github.com/AgoraIO/OpenDuo-iOS-Objective-C/issues)

## License

The MIT License (MIT). 
