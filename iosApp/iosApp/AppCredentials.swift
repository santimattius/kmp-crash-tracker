//
//  AppCredentials.swift
//  iosApp
//
//  Created by Santiago Mattiauda on 29/2/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

struct AppCredentials: Decodable {
    private enum CodingKeys: String, CodingKey {
        case bugsnagApiKey
    }

    let bugsnagApiKey: String

}
