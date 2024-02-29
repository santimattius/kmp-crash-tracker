//
//  AppDelegate.swift
//  iosApp
//
//  Created by Santiago Mattiauda on 28/2/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import UIKit
import Shared

class AppDelegate: NSObject, UIApplicationDelegate {
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        let appCredentials = readAppCredentials()
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
