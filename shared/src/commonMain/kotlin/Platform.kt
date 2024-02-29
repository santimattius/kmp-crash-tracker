interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun log(text: String)