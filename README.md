# KMP Crash Tracker
This is a Kotlin Multiplatform project targeting Android, iOS.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

* `/shared` is for the code that will be shared between all targets in the project.
  The most important subfolder is `commonMain`. If preferred, you can add code to the platform-specific folders here too.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)

## Setup Project

### Bugsnag
Create your mobile project in https://www.bugsnag.com/ and configure your apiKeys on Android and iOS.

**Android**
Using local properties for define api key:

```properties
bugsnagApiKey="{your-api-key}"
```
**iOS**
Add in the [Credentials.plist](https://github.com/santimattius/kmp-crash-tracker/blob/main/iosApp/iosApp/Credentials.plist) file the following variables bugsnagApiKey with the keys obtained in the bugsnag portal.

#### Send dSYMS to Bugsnag

Check official documentation: https://docs.bugsnag.com/platforms/ios/showing-full-stacktraces/

### Crashlytics
Check Firebase Crashlytics [documentation](https://firebase.google.com/docs/crashlytics) and create your Firebase projects for android and ios. Note, if your repo is public, you probably want to make sure to add Android's google-services.json and iOS's GoogleService-info files to your gitignore. 

#### Send dSYMS for Dynamic Framework

If you're using a dynamic framework you may also see a warning on the Crashlytics dashboard about missing dSYMS or "Missing UUID" in the stack trace of Kotlin crashes. If that happens adding a build phase to push the Kotlin framework dSYM to Crashlytics separately should resolve it.

In Xcode, select your project on the left sidebar, open the Build Phases Tab, and press the + in the top left. The select "New Run Script Phase" and give it a name like "Upload Kotlin dSYM". Add this script to the build phase

```shell
Pods/FirebaseCrashlytics/upload-symbols -gsp iosApp/GoogleService-Info.plist -p ios ../shared/build/cocoapods/framework/Shared.framework.dSYM
```

<p align="center">
  <img src="https://github.com/santimattius/kmp-crash-tracker/blob/main/capture/crashlytics-dsym.png?raw=true" alt="rule"/>
</p>

## Init library
This library support Bugsnag and Crashlytics. Default case add two providers.

### Android

Add next line into Application class

```kotlin
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Init crash tracker
        CrashTracker.initialize(
            config = CrashTrackerConfig(
                context = this,
                apiKey = BuildConfig.bugsnagApiKey
            ),
            providers = Providers.Default
        )
    }
}
```

### iOS

Add next line into appdelegate class

```swift
class AppDelegate: NSObject, UIApplicationDelegate {
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        let appCredentials = readAppCredentials()
       // Init crash tracker
        CrashTracker.companion.initialize(
            config: CrashTrackerConfig(
                apiKey: appCredentials.bugsnagApiKey
            ),
            providers: .bugsnag
        )

        return true
    }

    func readAppCredentials() -> AppCredentials {
          let url = Bundle.main.url(forResource: "Credentials", withExtension: "plist")!
          let data = try! Data(contentsOf: url)
          let decoder = PropertyListDecoder()
          return try! decoder.decode(AppCredentials.self, from: data)
      }
}
```

## References
- [CrashKiOS - KMM Crash Reporting](https://crashkios.touchlab.co/)
- [BugSnag documentation](https://docs.bugsnag.com/)
- [Firebase Crashlytics](https://firebase.google.com/docs/crashlytics?hl=es-419)
- [Add dependencies on a Pod library in KMP﻿](https://kotlinlang.org/docs/native-cocoapods-libraries.html)
